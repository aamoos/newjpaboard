package jpa.board.service;

import jpa.board.entity.Board;
import jpa.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Board deleteBoard(Long id){
        Board board = boardRepository.findById(id).get();

        //플래그값이 Y이면 논리삭제
        board.delete("Y");
        return board;
    }

}
