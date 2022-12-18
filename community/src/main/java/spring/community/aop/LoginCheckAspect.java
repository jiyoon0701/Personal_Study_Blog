package spring.community.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Aspect
@Slf4j
@Component
public class LoginCheckAspect {

    @Before("execution(* spring.community.controller.BoardController.*(..))")
    public void loginCheck(JoinPoint joinPoint) throws IOException {
        Object[] jb = joinPoint.getArgs();
        HttpSession session = (HttpSession) jb[0];
        try {
            HttpServletResponse response = (HttpServletResponse) jb[2];
            response.setContentType("text/html; charset=UTF-8");
            String email = (String) session.getAttribute("email");
            PrintWriter writer = response.getWriter();
            if(email == null){
                writer.print("<script>");
                writer.print("alert('로그인을 해주세요');");
                writer.print("location.href='/user/login';");
                writer.print("</script>");
                writer.close();
            }
        }
        catch (Exception e){
            log.info("Not response");
        }



    }
}

