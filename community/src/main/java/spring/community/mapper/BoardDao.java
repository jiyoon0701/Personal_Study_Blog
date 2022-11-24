package spring.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.community.domain.User;

@Repository
@Mapper
public interface BoardDao {
    public void write(User user);
}
