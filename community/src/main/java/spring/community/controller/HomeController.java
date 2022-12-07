package spring.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.community.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView home(HttpSession session){
        ModelAndView ma = new ModelAndView();
        String email = (String) session.getAttribute("email");
        try{
            String userID = String.valueOf((int) session.getAttribute("userID"));
            Map<String, Object> maps = new HashMap<String , Object>();
            maps.put("USER_ID", Integer.parseInt(userID));
            String it = userService.userItInfo(maps).toString();
            it = it.substring(1, it.length()-1);
            ma.addObject("IT", it);
        }catch (NullPointerException e){
            System.out.println("로그인 해야됩니다~");
        }

        String userName = (String) session.getAttribute("userName");
        ma.addObject("email",email);
        ma.addObject("userName", userName);
        ma.setViewName("index");
        return ma;
    }
}
