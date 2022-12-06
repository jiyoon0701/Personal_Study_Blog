package spring.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.community.domain.User;
import spring.community.domain.User_IT;
import spring.community.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @PostMapping("login")
    public ResponseEntity<String> loginDo(@RequestParam Map<String, Object> param, HttpSession session, User user) {
        ResponseEntity<String> entity = null;

        user.setUSER_EMAIL((String) param.get("USER_EMAIL"));
        user.setUSER_PASS((String) param.get("USER_PASS"));
        User userVO = null;
        try { //아이디가 없는 경우 예외처리
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.valueOf(200));
            userVO = service.login(user);
            if (userVO.getUSER_PASS().equals(user.getUSER_PASS())) {
                session.setAttribute("email", userVO.getUSER_EMAIL());
                session.setAttribute("userID",userVO.getUSER_ID());
            }

        } catch (Exception e) {
            entity = new ResponseEntity<String>("FAIL", HttpStatus.valueOf(200));
        }
        return entity;
    }

    @GetMapping("join")
    public String registerView() {
        return "login";
    }


    @PostMapping("join")
    public ResponseEntity<String>  register(@RequestParam Map<String, Object> param, User user) {
        ResponseEntity<String> entity = null;
        Map<String,Object> map = new HashMap<String, Object>();
        List<User_IT> userIT = new ArrayList<>();
        String IT = (String) param.get("IT");
        String USER_EMAIL = (String)param.get("USER_EMAIL");
        String USER_PASS = (String)param.get("USER_PASS");
        String USER_NAME = (String)param.get("USER_NAME");
        int USER_AGE = Integer.parseInt((String)param.get("USER_AGE"));

        IT = IT.replace(",", "");

        int[] arr = new int[IT.length()];

        user.setUSER_EMAIL(USER_EMAIL);
        user.setUSER_PASS(USER_PASS);
        user.setUSER_NAME(USER_NAME);
        user.setUSER_AGE(USER_AGE);
        map.put("USER_EMAIL",USER_EMAIL);
        try {
            int emailCk = service.emailCheck(map);

            if(emailCk >= 1) {
                entity = new ResponseEntity<String>("FAIL", HttpStatus.valueOf(200));
            }
            else {
                // 회원가입
                service.join(user);
                for (int i = 0; i < IT.length(); i++) {
                    int it = Integer.parseInt(String.valueOf(IT.charAt(i)));
                    userIT.add(new User_IT(user.getUSER_ID(), it));
                }
                System.out.println(user.getUSER_ID() + "삽입");
                service.userIt(userIT);
                entity = new ResponseEntity<String>("SUCCESS", HttpStatus.valueOf(200));
            }
        } catch (Exception e) {
            System.out.print("error");
        }
        return entity;
    }

    @GetMapping("mypage")
    public ModelAndView UserMypage(HttpSession session){
        ModelAndView ma = new ModelAndView();
        int userID = (int) session.getAttribute("userID");
        Map<String, Object> maps = new HashMap<String , Object>();

        maps.put("USER_ID", userID);
        List it = service.userItInfo(maps);
        User user = service.userInfo(maps);

        ma.addObject("it", it);
        ma.addObject("user",user);
        ma.setViewName("user/mypage");
        return ma;
    }

    @GetMapping("logout")
    public String userLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}