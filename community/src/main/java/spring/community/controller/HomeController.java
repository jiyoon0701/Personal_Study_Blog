package spring.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(HttpSession session){
        ModelAndView ma = new ModelAndView();
        String email = (String) session.getAttribute("email");
        ma.addObject("email",email);
        ma.setViewName("index");
        return ma;
    }
}
