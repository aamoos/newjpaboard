package jpa.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * packageName    : jpa.board.entity
 * fileName       : Test
 * author         : 김재성
 * date           : 2022-08-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-09        김재성       최초 생성
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Test {

    private Long id;
    private String name;
    private String telNo;
    private int age;

    @Builder
    public Test(Long id, String name, String telNo, int age){
        this.id = id;
        this.name = name;
        this.telNo = telNo;
        this.age = age;
    }

}
