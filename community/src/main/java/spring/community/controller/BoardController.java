package spring.community.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spring.community.config.S3UploaderService;
import spring.community.domain.Board;
import spring.community.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
@Slf4j
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    S3UploaderService s3Uploader;

    Map<String, Object> maps = new HashMap<String, Object>();

    ArrayList<MultipartFile> boardImg = new ArrayList<MultipartFile>();

    static int boardId = 0;

    @GetMapping("write")
    public ModelAndView board_write(HttpSession session, Board board) {
        String email = (String) session.getAttribute("email");
        ModelAndView ma = new ModelAndView();
        int userID = (int) session.getAttribute("userID");
        board.setUSER_ID(userID);
        board.setSTATE("false");
        boardService.boardCreate(board);
        int id = board.getBOARD_ID();
        boardId = id;
        log.info("board id : " + id);
        ma.addObject("boardID", boardId);
        ma.addObject("content", board);
        ma.setViewName("board/write");
        return ma;
    }

    @ResponseBody
    @PostMapping("imageupload")
    public String imageUpload(@RequestParam("image") MultipartFile multipartFile, HttpSession session) throws IOException {

        if (multipartFile.isEmpty()) {
            System.out.println("error ");
            return "not found";
        }

        boardImg.add(multipartFile);
        int userID = (int) session.getAttribute("userID");
        // 유저의 아이디 번호로 경로 지정
        String dir = "Community/" + userID + "/" + boardId;
        String url = null;
        try {
            url = s3Uploader.upload(multipartFile, dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    @PostMapping("save")
    public String board_write(@RequestParam Map<String, Object> map, Board board) {
        maps.clear();
        maps.put("TITLE", map.get("TITLE"));
        maps.put("CONTENT_HTML", map.get("content_HTML"));
        maps.put("CONTENT_MARK", map.get("content_MARK"));
        maps.put("REPRE_IMAGE", map.get("file_NAME"));
        maps.put("BOARD_ID", Integer.parseInt((String) map.get("BOARD_ID")));
        maps.put("TAG", (String) map.get("TAG"));
        maps.put("STATE", map.get("STATE"));
        maps.put("RATING", Integer.parseInt((String) map.get("RATING")));
        log.info("save id: ", boardId);
        // 게시글 작성
        if (maps.get("REPRE_IMAGE").equals("null")) {
            boardService.boardUpdate(maps);
        } else {
            boardService.boardSaveUpdate(maps);
        }

        return "redirect:/";

    }

    @GetMapping("view/{state}") // 내가 작성한 글 반환
    public ModelAndView boardView(HttpSession session, @PathVariable("state") String state) {
        ModelAndView ma = new ModelAndView();
        maps.clear();
        String email = (String) session.getAttribute("email");
        int userID = (int) session.getAttribute("userID");
        maps.put("USER_EMAIL", email);
        maps.put("USER_ID", userID);
        maps.put("STATE", state);
        List<Board> boardList = boardService.boardView(maps);
        ma.addObject("boardList", boardList);
        ma.addObject("state", state);
        ma.addObject("email", email);
        ma.setViewName("board/view");
        return ma;
    }

    @GetMapping("contentView/{boardID}")
    public ModelAndView contentView(@PathVariable("boardID") int boardID) {
        ModelAndView ma = new ModelAndView();
        Board contentView = boardService.boardContentView(boardID);
        ma.addObject("content", contentView);
        ma.setViewName("board/contentView");
        return ma;
    }

    @GetMapping("allview")
    public ModelAndView boardAllView() {
        ModelAndView ma = new ModelAndView();
        List<Board> boardList = boardService.boardAllView(); // 내가 작성한 글
        ma.addObject("boardList", boardList);
        ma.setViewName("board/view");
        return ma;
    }

    @GetMapping("delete/{boardID}") // 게시글 삭제
    public String boardDelete(@PathVariable("boardID") int boardID) {
        boardService.boardDelete(boardID);
        return "redirect:/board/view/true";
    }

    @GetMapping("update/{boardID}") // 업데이트를 위한 수정 페이지를 띄워줘야함
    public ModelAndView boardUpdate(@PathVariable("boardID") int boardID) {
        ModelAndView ma = new ModelAndView();
        Board contentView = boardService.boardContentView(boardID);
        ma.addObject("content", contentView);
        ma.addObject("boardID", contentView.getBOARD_ID());
        ma.setViewName("board/write");
        return ma;
    }

    @GetMapping("search")
    public ModelAndView boardSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "state") String state, HttpSession session){
        ModelAndView ma = new ModelAndView();
        List<Board> boardSearchList = boardService.boardSearchList(keyword, state);
        ma.addObject("boardList", boardSearchList);
        ma.addObject("state", state);
        String email = (String) session.getAttribute("email");
        ma.setViewName("board/view");
        ma.addObject("email",email);
        return ma;
    }
}
