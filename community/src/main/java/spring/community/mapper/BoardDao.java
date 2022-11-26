package spring.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.community.domain.Board;
import spring.community.domain.User;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardDao {
    public void save(Map<String, Object> param);
    public List<Board> boardViewContent(Map<String, Object> param);
}
