package jpa.board.entity;

import jpa.board.dto.BoardDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
    * @methodName : update
    * @date : 2022-08-05 오후 3:51
    * @author : 김재성
    * @Description: 게시판 업데이트
    **/
    public Board update(String title, String content){
        this.title = title;
        this.content = content;
        return this;
    }

    /**
    * @methodName : updateViewCount
    * @date : 2022-08-05 오후 3:51
    * @author : 김재성
    * @Description: 게시판 조회수 증가
    **/
    public Board updateViewCount(Long viewCount){
        this.viewCount = viewCount+1;
        return this;
    }

    /**
    * @methodName : delete
    * @date : 2022-08-05 오후 3:51
    * @author : 김재성
    * @Description: 게시판 삭제
    **/
    public Board delete(String delYn){
        this.delYn = delYn;
        return this;
    }

    @Builder
    public Board(String title, String content, Member member){
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.delYn = "N";
        this.member = member;
    }
}
