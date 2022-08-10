package jpa.board.repository;

import jpa.board.dto.BoardDto;
import jpa.board.dto.BoardFileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * packageName    : jpa.board.repository
 * fileName       : CustomBoardRepository
 * author         : 김재성
 * date           : 2022-08-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-02        김재성       최초 생성
 */
public interface CustomBoardRepository {
    Page<BoardDto> selectBoardList(String searchVal, Pageable pageable);
    BoardFileDto selectBoardDetail(Long boardId);
}
