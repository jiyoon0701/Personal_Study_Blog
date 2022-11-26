package spring.community.service;


import spring.community.domain.Board;

import java.util.List;
import java.util.Map;


public interface BoardService {
    public void save(Map<String, Object> param);
    public List<Board> boardViewContent(Map<String, Object> param);
}