package jpa.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * packageName    : jpa.board.entity
 * fileName       : BoardFile
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
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_file_id")
    private Long id;            //번호

    private Long boardId;
    private String delYn;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Builder
    public BoardFile(Long boardId, Long fileId, String delYn, File file){
        this.boardId = boardId;
        this.delYn = "N";
        this.file = file;
    }
}
