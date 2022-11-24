package spring.community.service;

import spring.community.domain.User;
import spring.community.domain.User_IT;

import java.util.List;
import java.util.Map;


public interface UserService {
    public void join(User user);
    public User login(User user);
    public int emailCheck(Map<String, Object> param);
    public void userIt(List<User_IT> it);
}