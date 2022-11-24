package spring.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.community.domain.User;
import spring.community.domain.User_IT;
import spring.community.mapper.UserDao;

import java.util.List;
import java.util.Map;

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
            userVO = null;
        }
        return userVO;
    }

    @Override
    public int emailCheck(Map<String, Object> param) {
        return userDao.emailCheck(param);
    }

    public void userIt(List<User_IT> it) {
        userDao.userIt(it);
    }
}