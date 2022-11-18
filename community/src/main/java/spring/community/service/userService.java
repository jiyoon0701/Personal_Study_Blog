package spring.community.service;

import spring.community.domain.User;
import spring.community.domain.User_IT;

import java.util.List;


public interface UserService {
    public void join(User user);
    public User login(String user_id);

    public void userIt(List<User_IT> it);
}
