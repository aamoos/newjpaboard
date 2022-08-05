package jpa.board.dto;

import jpa.board.entity.Board;
import jpa.board.entity.File;
import jpa.board.entity.Member;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

/**
 * packageName    : jpa.board.dto
 * fileName       : FileDto
 * author         : 김재성
 * date           : 2022-08-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-05        김재성       최초 생성
 */

@Data
public class FileDto {

    private Long id;                //id

    private String originFileName;  //원본 파일명

    private String fullPath;        //filePath

    public FileDto(){

    }

    @Builder
    public FileDto(Long id, String originFileName, String fullPath){
        this.id = id;
        this.originFileName = originFileName;
        this.fullPath = fullPath;
    }

    public File toEntity(){
        return File.builder()
                .originFileName(originFileName)
                .fullPath(fullPath)
                .build();
    }

}
