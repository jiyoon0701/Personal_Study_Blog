package spring.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.community.domain.User;
import spring.community.domain.User_IT;
import spring.community.mapper.UserDao;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public void join(User user) {
         userDao.join(user);
    }
    public User login(User  user) {
        User userVO = null;
        try {
            userVO = userDao.readUserWithIDPW(user.getUSER_EMAIL(), user.getUSER_PASS());
            System.out.println("S: 로그인 아디: "+userVO.getUSER_EMAIL()+" 비번: "+userVO.getUSER_PASS());
        } catch (Exception e) {
            //e.printStackTrace();
            userVO = null; //실행하다 문제가 생겼을때 해당 데이터를 보내지않겠다는 의미 = 예외처리
        }
        return userVO;
    }

    public void userIt(List<User_IT> it) {
        userDao.userIt(it);
    }
}