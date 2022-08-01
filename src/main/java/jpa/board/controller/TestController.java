package jpa.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * packageName    : jpa.board.controller
 * fileName       : MainController
 * author         : 김재성
 * date           : 2022-08-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-01        김재성       최초 생성
 */

@Controller
public class TestController {
    @GetMapping("/test")
    public String main(){
        return "views/content.html";
    }
}