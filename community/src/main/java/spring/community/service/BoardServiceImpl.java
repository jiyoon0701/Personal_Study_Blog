package spring.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.community.domain.Board;
import spring.community.mapper.BoardDao;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDao boardDao;

    public void boardCreate(Board board) {
         boardDao.boardCreate(board);
    }

    @Override
    public List<Board> boardView(Map<String, Object> param) {
        return boardDao.boardView(param);
    }

    @Override
    public Board boardContentView(Integer BOARD_ID) {
        return boardDao.boardContentView(BOARD_ID);
    }

    @Override
    public List<Board> boardAllView() {
        return boardDao.boardAllView();
    }

    @Override
    public void boardSaveUpdate(Map<String, Object> param) {
        boardDao.boardSaveUpdate(param);
    }

    @Override
    public void boardUpdate(Map<String, Object> param) {
        boardDao.boardUpdate(param);
    }

    @Override
    public void boardDelete(int boardID) {
        boardDao.boardDelete(boardID);
    }

    @Override
    public List<Board> boardSearchList(String keyword, String state) {
        return boardDao.boardSearchList(keyword, state);
    }


}