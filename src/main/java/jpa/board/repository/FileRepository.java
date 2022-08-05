package jpa.board.repository;

import jpa.board.entity.Board;
import jpa.board.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : jpa.board.repository
 * fileName       : FileRepository
 * author         : 김재성
 * date           : 2022-08-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-05        김재성       최초 생성
 */
public interface FileRepository extends JpaRepository<File, Long> {
}
