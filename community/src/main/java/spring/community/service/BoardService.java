package spring.community.service;


import spring.community.domain.Board;

import java.util.List;
import java.util.Map;


public interface BoardService {
    public void boardCreate(Board board);
    public List<Board> boardView(Map<String, Object> param);
    public Board boardContentView(Integer BOARD_ID);
    public List<Board> boardAllView();
    public void boardUpdate(Map<String, Object> param);

    public void boardDelete(int boardID);
}