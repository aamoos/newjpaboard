package jpa.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * packageName    : jpa.board.entity
 * fileName       : Test
 * author         : 김재성
 * date           : 2022-08-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-01        김재성       최초 생성
 */

@Entity
public class Test {
    @Id @GeneratedValue
    @Column(name = "test_id")
    private Long id;

    private String name;
}
