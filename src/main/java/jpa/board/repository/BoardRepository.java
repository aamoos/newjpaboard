package jpa.board.repository;

import jpa.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : jpa.board.repository
 * fileName       : BoardRepository
 * author         : 김재성
 * date           : 2022-08-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-01        김재성       최초 생성
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
}
