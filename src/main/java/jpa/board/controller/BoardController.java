package jpa.board.controller;

import jpa.board.dto.BoardDto;
import jpa.board.entity.Board;
import jpa.board.repository.BoardRepository;
import jpa.board.repository.CustomBoardRepository;
import jpa.board.service.BoardService;
import jpa.board.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.function.LongToIntFunction;

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
    private final BoardService boardService;

    private final FileService fileService;

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
    public String write(Model model){
        model.addAttribute("boardDto", new BoardDto());
        return "board/write";
    }

    /**
    * @methodName : save
    * @date : 2022-08-03 오후 2:15
    * @author : 김재성
    * @Description: 게시판 글 등록
    **/
    @PostMapping("/write")
    public String save(@Valid BoardDto boardDto, BindingResult result) throws Exception {

        //유효성검사 걸릴시
        if(result.hasErrors()){
            return "board/write";
        }

        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    /**
    * @methodName : update
    * @date : 2022-08-02 오후 2:07
    * @author : 김재성
    * @Description: 게시판 수정화면
    **/
    @GetMapping("/update/{boardId}")
    public String detail(@PathVariable Long boardId, Model model){
        Board board = boardService.selectBoardDetail(boardId);

        BoardDto boardDto = BoardDto.builder()
                            .id(boardId)
                            .title(board.getTitle())
                            .content(board.getContent())
                            .build();
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("boardFile", customBoardRepository.selectBoardFileDetail(boardId));

        return "board/update";
    }

    /**
    * @methodName : update
    * @date : 2022-08-05 오전 11:20
    * @author : 김재성
    * @Description: 게시판 수정
    **/
    @PutMapping("/update/{boardId}")
    public String update(@Valid BoardDto boardDto, BindingResult result) throws Exception {
        //유효성검사 걸릴시
        if(result.hasErrors()){
            return "board/update";
        }

        boardService.saveBoard(boardDto);
        return "redirect:/";
    }

    /**
    * @methodName : delete
    * @date : 2022-08-05 오전 11:20
    * @author : 김재성
    * @Description: 게시판 목록 체크박스 선택 삭제
    **/
    @PostMapping("/delete")
    public String delete(@RequestParam List<String> boardIds){

        for(int i=0; i<boardIds.size(); i++){
            Long id = Long.valueOf(boardIds.get(i));
            boardService.deleteBoard(id);
        }

        return "redirect:/";
    }

    /**
    * @methodName : boardFileDelete
    * @date : 2022-08-10 오후 5:23
    * @author : 김재성
    * @Description: 파일삭제
    **/
    @PostMapping("/boardFileDelete")
    public String boardFileDelete(@RequestParam Long fileId, @RequestParam Long boardId){

        //게시판 파일삭제
        fileService.deleteBoardFile(fileId);

        return "redirect:/update/"+boardId;
    }
}
