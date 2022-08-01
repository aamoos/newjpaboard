package jpa.board.repository;

import jpa.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : jpa.board.repository
 * fileName       : MemberRepository
 * author         : 김재성
 * date           : 2022-08-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-01        김재성       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
