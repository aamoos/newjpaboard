package jpa.board.entity;

import jpa.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void 게시판_등록(){
        Board board = Board.builder()
                .title("타이틀1")
                .content("내용1")
                .build();
        boardRepository.save(board);
    }

}