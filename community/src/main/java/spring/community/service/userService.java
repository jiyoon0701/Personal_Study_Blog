package spring.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.community.domain.User;
import spring.community.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    private final UserRepository userRepository;

    @Autowired
    public userService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * 회원가입
     * */
    public void join(User user) {
        // 같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { // 값이 있으면 동작하는 것 . 이것은 optional 때문에 가능한거임.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

       // validateDuplicateMember(user); // 중복 회원 검증
        userRepository.save(user);

    }

    private void validateDuplicateMember(User user) {
        userRepository.findById(user.getId())
                .ifPresent(m -> { // 값이 있으면 동작하는 것 . 이것은 optional 때문에 가능한거임.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public User login(String id) {
        return  userRepository.login(id);
    }

    /*
     *   전체 회원 조회
     * */
    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(String id){
        return userRepository.findById(id);
    }
}
