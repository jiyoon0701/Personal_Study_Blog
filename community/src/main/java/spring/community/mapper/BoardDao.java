package spring.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.community.domain.Board;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardDao {
    public void boardCreate(Board board);
    public List<Board> boardView(Map<String, Object> param);
    public Board boardContentView(Integer BOARD_ID);
    public List<Board> boardAllView();

    public void boardUpdate(Map<String, Object> param);
    public void boardSaveUpdate(Map<String, Object> param);

    public void boardDelete(int boardID);

    public List<Board> boardSearchList(String keyword, String state);
}
