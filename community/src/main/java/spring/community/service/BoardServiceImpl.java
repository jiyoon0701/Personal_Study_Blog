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

    public void save(Map<String, Object> param) {
         boardDao.save(param);
    }

    @Override
    public List<Board> boardView(Map<String, Object> param) {
        return boardDao.boardView(param);
    }

    @Override
    public Board boardContentView(Integer BOARD_ID) {
        return boardDao.boardContentView(BOARD_ID);
    }
}