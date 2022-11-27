package spring.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.community.domain.Board;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardDao {
    public void save(Map<String, Object> param);
    public List<Board> boardView(Map<String, Object> param);
    public Board boardContentView(Integer BOARD_ID);
}
