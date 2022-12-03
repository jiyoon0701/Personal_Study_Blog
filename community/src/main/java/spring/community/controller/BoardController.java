package spring.community.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    Map<String, Object> maps = new HashMap<String , Object>();

    ArrayList<MultipartFile> boardImg = new ArrayList<MultipartFile>();

    static int boardId = 0;

    @GetMapping("write")
    public String board_write(HttpSession session, Board board){
        String email = (String)session.getAttribute("email");
        int userID = (int) session.getAttribute("userID");
        board.setUSER_ID(userID);
        boardService.boardCreate(board);
        int id = board.getBOARD_ID();
        boardId = id;
        log.info("board id : "+id);
        return "board/write";
    }

    @ResponseBody
    @PostMapping("imageupload")
    public String imageUpload(@RequestParam("image") MultipartFile multipartFile, HttpSession session) throws IOException {

        if(multipartFile.isEmpty()) {
            System.out.println("error ");
            return "not found";
        }

        boardImg.add(multipartFile);
        int userID = (int) session.getAttribute("userID");
        // 유저의 아이디 번호로 경로 지정
        String dir = "Community/"+userID + "/" + boardId;
        String url = null;
        try {
            url = s3Uploader.upload(multipartFile, dir);
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return url;
    }

    @PostMapping("save")
    public String board_write(@RequestParam Map<String,Object> map){
        maps.clear();
        maps.put("TITLE", map.get("TITLE"));
        maps.put("CONTENT_HTML", map.get("content_HTML"));
        maps.put("CONTENT_MARK", map.get("content_MARK"));
        maps.put("REPRE_IMAGE", map.get("file_NAME"));
        maps.put("BOARD_ID", boardId);
        maps.put("TAG", (String)map.get("TAG"));
        maps.put("STATE", (boolean)map.get("STATE"));
        log.info("save id: ", boardId);
        // 게시글 작성
        boardService.boardUpdate(maps);
        return "redirect:/";

    }

    @GetMapping("view/{state}") // 내가 작성한 글 반환
    public ModelAndView boardView(HttpSession session, @PathVariable("state") boolean state){
        ModelAndView ma = new ModelAndView();
        maps.clear();
        String email = (String)session.getAttribute("email");
        int userID = (int) session.getAttribute("userID");
        maps.put("USER_EMAIL", email);
        maps.put("USER_ID", userID);
        maps.put("STATE", state);
        List<Board> boardList = boardService.boardView(maps);
        ma.addObject("boardList", boardList);
        ma.setViewName("board/view");
        return ma;
    }

    @GetMapping("contentView/{boardID}")
    public ModelAndView contentView(@PathVariable("boardID") int boardID){
        ModelAndView ma = new ModelAndView();
        Board contentView = boardService.boardContentView(boardID);
        ma.addObject("content", contentView);
        ma.setViewName("board/contentView");
        return  ma;
    }

    @GetMapping("allview")
    public ModelAndView boardAllView(){
        ModelAndView ma = new ModelAndView();
        List<Board> boardList  = boardService.boardAllView(); // 내가 작성한 글
        ma.addObject("boardList", boardList);
        ma.setViewName("board/view");
        return ma;
    }

    @GetMapping("delete") // 게시글 삭제
    public String boardDelete(){
        return "redirect:/view";
    }
}
