package spring.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.community.domain.User;
import spring.community.domain.User_IT;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    public void join(User user);
    public void userIt(List<User_IT> it);

    public User readUserWithIDPW(String USER_EMAIL, String USER_PASS);
    public List<String> userItInfo(Map<String, Object> param);

    public User userInfo(Map<String, Object> param);
    int emailCheck(Map<String, Object> param);
}