package spring.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spring.community.domain.Board;
import spring.community.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    BoardService boardService;

    Map<String, Object> maps = new HashMap<String , Object>();

    @GetMapping("write")
    public String board_write(){
        return "board/write";
    }

    @ResponseBody
    @PostMapping("imageupload")
    public String imageUpload(@RequestParam("image") MultipartFile multipartFile,
                              @RequestParam String uri, HttpServletRequest request) {

        if(multipartFile.isEmpty()) {
            System.out.println("error ");
            return "not found";
        }

        String directory = request.getServletContext().getRealPath("/static/upload/");

        String fileName = multipartFile.getOriginalFilename();
        int lastIndex = fileName.lastIndexOf(".");
        String ext = fileName.substring(lastIndex, fileName.length());
        String newFileName = LocalDate.now() + "_" + System.currentTimeMillis() + ext;

        try {
            File image = new File(directory + newFileName);
            multipartFile.transferTo(image);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("uri : {}"+ uri);
            System.out.println("Image Path : {}"+ directory);
            System.out.println("File_name : {}" + newFileName);
        }

        // 주소값 알아내기
        String path = request.getContextPath();
        int index = request.getRequestURL().indexOf(path);
        String url = request.getRequestURL().substring(0, index);

        // https://localhost:80801/webapp/upload/파일이름

        return url + request.getContextPath() + "/static/upload/" + newFileName;
    }

    @PostMapping("save")
    public String board_write(@RequestParam Map<String,Object> map, HttpSession session){
        maps.clear();
        maps.put("TITLE", map.get("TITLE"));
        maps.put("CONTENT_HTML", map.get("content_HTML"));
        maps.put("CONTENT_MARK", map.get("content_MARK"));
        maps.put("USER_EMAIL", (String)session.getAttribute("email"));
        //ModelAndView ma = new ModelAndView();
        //ma.addObject("TITLE",map.get("TITLE"));
        boardService.save(maps);

        return "redirect:/";
    }

    @GetMapping("view")
    public ModelAndView boardView(HttpSession session){
        ModelAndView ma = new ModelAndView();
        maps.clear();
        String email = (String)session.getAttribute("email");
        maps.put("USER_EMAIL", email);
        List<Board> boardList  = boardService.boardViewContent(maps);
        ma.addObject("boardList", boardList);
        ma.setViewName("board/view");
        System.out.println(boardList.get(0).getTITLE());
        return ma;
    }

}
