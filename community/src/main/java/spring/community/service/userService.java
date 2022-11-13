package spring.community.service;

import spring.community.domain.IT;
import spring.community.domain.User;


public interface UserService {
    public void join(User user);
    public User login(String user_id);

    public void setItField(IT it);
}
