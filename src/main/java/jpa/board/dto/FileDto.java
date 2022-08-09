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

    private Long id;                    //id

    private String originFileName;      //원본 파일명

    private String savedFileName;       //저장된 파일명

    private String uploadDir;           //경로명

    private String extension;           //확장자

    private Long size;                  //파일 사이즈

    private String contentType;         //ContentType

    public FileDto(){

    }

    @Builder
    public FileDto(Long id, String originFileName, String savedFileName, String uploadDir
    , String extension, Long size, String contentType){
        this.id = id;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.uploadDir = uploadDir;
        this.extension = extension;
        this.size = size;
        this.contentType = contentType;
    }

    public File toEntity(){
        return File.builder()
                .originFileName(originFileName)
                .savedFileName(savedFileName)
                .uploadDir(uploadDir)
                .extension(extension)
                .size(size)
                .contentType(contentType)
                .build();
    }

}
