package spring.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.community.domain.Board;
import spring.community.domain.User;
import spring.community.domain.User_IT;
import spring.community.mapper.BoardDao;
import spring.community.mapper.UserDao;

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
    public List<Board> boardViewContent(Map<String, Object> param) {
        return boardDao.boardViewContent(param);
    }
}