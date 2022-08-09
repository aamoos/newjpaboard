package jpa.board.service;

import jpa.board.dto.BoardDto;
import jpa.board.dto.FileDto;
import jpa.board.entity.Board;
import jpa.board.entity.Member;
import jpa.board.repository.BoardRepository;
import jpa.board.repository.FileRepository;
import jpa.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * packageName    : jpa.board.service
 * fileName       : BoardService
 * author         : 김재성
 * date           : 2022-08-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-03        김재성       최초 생성
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private final FileService fileService;

    /**
     * @methodName : saveBoard
     * @date : 2022-08-03 오후 2:13
     * @author : 김재성
     * @Description: 글등록
     **/

    @Transactional
    public Long saveBoard(BoardDto boardDto) throws Exception {
        List<Member> memberList = memberRepository.findAll();
        Member member = memberList.get(0);
        Board board = null;

        //insert
        if(boardDto.getId() == null){
            board = boardDto.toEntity(member);
            boardRepository.save(board);
        }

        //update
        else{
            board = boardRepository.findById(boardDto.getId()).get();
            board.update(boardDto.getTitle(), boardDto.getContent());
        }

        //파일 저장
        fileService.saveFile(boardDto, board.getId());

        return board.getId();
    }

    /**
    * @methodName : deleteBoard
    * @date : 2022-08-03 오후 2:14
    * @author : 김재성
    * @Description: 글 삭제
    **/
    @Transactional
    public Board deleteBoard(Long id){
        Board board = boardRepository.findById(id).get();

        //플래그값이 Y이면 논리삭제
        board.delete("Y");
        return board;
    }

    /**
    * @methodName : selectBoardDetail
    * @date : 2022-08-09 오후 3:05
    * @author : 김재성
    * @Description: 게시판 상세
    **/
    @Transactional
    public Board selectBoardDetail(Long id){
        Board board = boardRepository.findById(id).get();
        board.updateViewCount(board.getViewCount());
        return board;
    }
}
