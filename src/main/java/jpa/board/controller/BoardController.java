package jpa.board.controller;

import jpa.board.dto.BoardDto;
import jpa.board.entity.Board;
import jpa.board.repository.CustomBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequiredArgsConstructor
public class BoardController {

    private final CustomBoardRepository customBoardRepository;

    /**
    * @methodName : list
    * @date : 2022-08-02 오후 2:07
    * @author : 김재성
    * @Description: 게시판 목록화면
    **/
    @GetMapping("/")
    public String list(String searchVal, Pageable pageable, Model model){
        Page<BoardDto> results = customBoardRepository.selectBoardList(searchVal, pageable);
        model.addAttribute("list", results);
        model.addAttribute("maxPage", 5);
        model.addAttribute("searchVal", searchVal);

        pageModelPut(results, model);
        return "board/list";
    }

    /**
    * @methodName : pageModelPut
    * @date : 2022-08-02 오후 4:36
    * @author : 김재성
    * @Description: pagenation 관련 값 model 넣기
    **/
    private void pageModelPut(Page<BoardDto> results, Model model){
        model.addAttribute("totalCount", results.getTotalElements());
        model.addAttribute("size",  results.getPageable().getPageSize());
        model.addAttribute("number",  results.getPageable().getPageNumber());
    }

    /**
    * @methodName : write
    * @date : 2022-08-02 오후 2:07
    * @author : 김재성
    * @Description: 게시판 글쓰기화면
    **/
    @GetMapping("/write")
    public String write(){
        return "board/write";
    }

    /**
    * @methodName : update
    * @date : 2022-08-02 오후 2:07
    * @author : 김재성
    * @Description: 게시판 수정화면
    **/
    @GetMapping("/update")
    public String update(){
        return "board/update";
    }
}
