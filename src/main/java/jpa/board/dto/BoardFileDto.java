package jpa.board.dto;

import jpa.board.entity.Board;
import jpa.board.entity.BoardFile;
import jpa.board.entity.File;
import lombok.Builder;
import lombok.Data;

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

    private Long fileId;

    public BoardFileDto(){

    }

    @Builder
    public BoardFileDto(Long boardId, Long fileId){
        this.boardId = boardId;
        this.fileId = fileId;
    }

    public BoardFile toEntity(){
        return BoardFile.builder()
                .boardId(boardId)
                .fileId(fileId)
                .build();
    }
}
