package spring.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ModelAndView loginDo(User user, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        User dbuser = null;
        try { //아이디가 없는 경우 예외처리
            dbuser = service.login(user.getUSER_EMAIL());
        } catch (EmptyResultDataAccessException e) {

            return mav;
        }
        if (user.getUSER_PASS().equals(dbuser.getUSER_PASS())) {
            session.setAttribute("loginUser", dbuser);
        } else {
            return mav;
        }
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping("register")
    public String registerView() {
        return "login";
    }

    @PostMapping("register")
    public ModelAndView register(@RequestParam Map<String, Object> param, User user) {
        ModelAndView mav = new ModelAndView();
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
        try {
            // 회원가입
          service.join(user);
            for (int i = 0; i < IT.length(); i++) {
                int it = Integer.parseInt(String.valueOf(IT.charAt(i)));
                userIT.add(new User_IT(user.getUSER_ID(), it));
            }
          System.out.println(user.getUSER_ID()+"삽입");
          service.userIt(userIT);

         //   mav.addObject("user", USER);
         } catch (DuplicateKeyException e){
           //  result.reject("error.duplicate.user");
            // mav.getModel().putAll(result.getModel());
             System.out.print("error");
             mav.addObject("error","아이디가 중복입니다.");
             return mav;
        }
        mav.setViewName("redirect:/");
        return mav;
}
        }
