package spring.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.community.domain.User;
import spring.community.service.userService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private userService userservice;

    @GetMapping("login")
    public String login(){
        return "user/login";
    }

    @PostMapping("login")
    public ModelAndView loginDo(User user, HttpSession session) {
            ModelAndView mav = new ModelAndView();

            User dbuser = null;
            try { //아이디가 없는 경우 예외처리
                dbuser = userservice.login(user.getId());
            } catch(EmptyResultDataAccessException e) {

                return mav;
            }
            if(user.getPassword().equals(dbuser.getPassword())) {
                session.setAttribute("loginUser", dbuser);
            } else {
//                bresult.reject("error.login.password");
//                mav.getModel().putAll(bresult.getModel());
                return mav;
            }
            mav.setViewName("redirect:/");
            return mav;
        }

    @GetMapping("register")
    public String registerView(){
        return "user/register";
    }

    @PostMapping("register")
    public ModelAndView register(User user, BindingResult result){
        ModelAndView mav = new ModelAndView();

        try {
            userservice.join(user);
            mav.addObject("user", user);
         } catch (DuplicateKeyException e){
             result.reject("error.duplicate.user");
             mav.getModel().putAll(result.getModel());
             System.out.print("error");
             mav.addObject("error","아이디가 중복입니다.");
             return mav;
        }
        mav.setViewName("redirect:/");
        return mav;
    }
}
