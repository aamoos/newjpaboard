package jpa.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * packageName    : jpa.board.controller
 * fileName       : BoardController
 * author         : 김재성
 * date           : 2022-08-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-01        김재성       최초 생성
 */
@Controller
public class BoardController {

    @GetMapping("/")
    public String list(){
        return "board/list";
    }

    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    @GetMapping("/update")
    public String update(){
        return "board/update";
    }
}
