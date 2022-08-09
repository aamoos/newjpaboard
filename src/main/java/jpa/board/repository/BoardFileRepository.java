package jpa.board.repository;

import jpa.board.entity.Board;
import jpa.board.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : jpa.board.repository
 * fileName       : BoardFileRepository
 * author         : 김재성
 * date           : 2022-08-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-09        김재성       최초 생성
 */
public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
}
