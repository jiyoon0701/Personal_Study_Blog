package spring.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("board")
public class BoardController {

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
    public String board_write(@RequestParam Map<String,Object> map){
        System.out.println(map.get("TITLE"));
        System.out.println(map.get("content_HTML"));
        //ModelAndView ma = new ModelAndView();
        //return
        return "redirect:/";
    }

}
