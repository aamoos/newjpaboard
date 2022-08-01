package jpa.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : jpa.board.entity
 * fileName       : Board
 * author         : 김재성
 * date           : 2022-08-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-01        김재성       최초 생성
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;            //번호

    private String title;       //제목
    private String content;     //내용

    @CreatedDate
    private LocalDateTime regDate;     //등록 날짜

    @LastModifiedDate
    private LocalDateTime uptDate;     //수정 날짜

    private Long viewCount;     //조회수
    private String delYn;       //삭제여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Board update(String title, String content, String delYn){
        this.title = title;
        this.content = content;
        this.delYn = delYn;
        return this;
    }

    @Builder
    public Board(String title, String content, Long viewCount, String delYn){
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.delYn = "N";
    }

}
