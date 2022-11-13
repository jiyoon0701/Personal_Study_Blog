package spring.community.controller;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.community.domain.IT;
import spring.community.domain.User;
import spring.community.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @GetMapping("login")
    public String login(){
        return "user/login";
    }

    @PostMapping("login")
    public ModelAndView loginDo(User user, HttpSession session) {
            ModelAndView mav = new ModelAndView();

            User dbuser = null;
            try { //아이디가 없는 경우 예외처리
                dbuser = service.login(user.getUSER_ID());
            } catch(EmptyResultDataAccessException e) {

                return mav;
            }
            if(user.getUSER_PASS().equals(dbuser.getUSER_PASS())) {
                session.setAttribute("loginUser", dbuser);
            } else {
                return mav;
            }
            mav.setViewName("redirect:/");
            return mav;
        }

    @GetMapping("register")
    public String registerView(){
        return "login";
    }

    @PostMapping("register")
    public ModelAndView register(@RequestParam Map<String, Object> param){
        // , @RequestParam(value = "IT", required = false) int[] IT
        ModelAndView mav = new ModelAndView();

        System.out.print(param.get("IT"));
        System.out.print(param.get("USER_ID"));
        try {
            // 회원가입
       //    service.join(USER);
            //service.setItField(itfield);

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
