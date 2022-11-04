package spring.community.repository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import spring.community.domain.User;

import javax.sql.DataSource;
import java.security.spec.NamedParameterSpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {
    // 여러 개의 파라미터가 있는 쿼리를 실행할 때는 JdbcTemplate보다 NamedParameterJdbcTemplate을 사용하는 것이 권장
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Map<String,Object> param = new HashMap<>();
    private RowMapper<User> mapper =
            new BeanPropertyRowMapper<>(User.class);
    public JdbcTemplateUserRepository(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        String Sql = "insert into user (id, name, password) values(:id, :name, :password)";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);
        // Bean객체로 파라미터 전달
        namedParameterJdbcTemplate.update(Sql,param);
        System.out.print("aa");
    }

    @Override
    public User login(String id) {
        param.clear();
        param.put("id", id);
//조회 결과가 없는 경우:EmptyResultDataAccessException 예외 발생
        return namedParameterJdbcTemplate.queryForObject("select * from user where id=:id", param, mapper);
    }

    @Override
    public Optional<User> findById(String id) {
        String Sql = "select count(*) from user where id = ?";

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
