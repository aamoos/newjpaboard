package jpa.board.service;

import jpa.board.dto.BoardDto;
import jpa.board.entity.Board;
import jpa.board.entity.Member;
import jpa.board.repository.BoardRepository;
import jpa.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
    * @methodName : selectBoard
    * @date : 2022-08-03 오후 5:50
    * @author : 김재성
    * @Description: 상세조회
    **/
    public Board selectBoardDetail(Long id){
        return boardRepository.findById(id).get();
    }

    /**
     * @methodName : saveBoard
     * @date : 2022-08-03 오후 2:13
     * @author : 김재성
     * @Description: 글등록
     **/

    @Transactional
    public Board saveBoard(BoardDto boardDto){
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

        return board;
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

}
