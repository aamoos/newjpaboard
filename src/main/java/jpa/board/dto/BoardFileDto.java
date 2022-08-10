package jpa.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import jpa.board.entity.Board;
import jpa.board.entity.BoardFile;
import jpa.board.entity.File;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : jpa.board.dto
 * fileName       : BoardFileDto
 * author         : 김재성
 * date           : 2022-08-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-09        김재성       최초 생성
 */

@Data
public class BoardFileDto {

    private Long id;

    private Long boardId;

    public BoardFileDto(){

    }

    @Builder
    public BoardFileDto(Long boardId){
        this.boardId = boardId;
    }

    public BoardFile toEntity(File file){
        return BoardFile.builder()
                .boardId(boardId)
                .file(file)
                .build();
    }
}
