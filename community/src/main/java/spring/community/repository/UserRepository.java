package spring.community.repository;
import spring.community.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user); // 회원가입

    User login(String id);
    Optional<User> findById(String id);
    List<User> findAll();
}
