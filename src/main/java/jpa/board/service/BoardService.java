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
    * @methodName : saveBoard
    * @date : 2022-08-03 오후 2:13
    * @author : 김재성
    * @Description: 글등록
    **/
    public Long saveBoard(BoardDto boardDto){
        List<Member> memberList = memberRepository.findAll();
        Member member = memberList.get(0);

        Board board = Board.builder()
                    .boardDto(boardDto)
                    .member(member)
                    .build();
        boardRepository.save(board);
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

}
