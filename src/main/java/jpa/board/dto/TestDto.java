package jpa.board.dto;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : jpa.board.dto
 * fileName       : TestDto
 * author         : 김재성
 * date           : 2022-08-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-09        김재성       최초 생성
 */

@Data
public class TestDto {

    private Long id;
    private String name;
    private String telNo;
    private int age;

    public TestDto(){

    }

    public TestDto(Long id){
        this.id = id;
    }

    public TestDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public TestDto(Long id, String name, String telNo){
        this.id = id;
        this.name = name;
        this.telNo = telNo;
    }

    @Builder
    public TestDto(Long id, String name, String telNo, int age){
        this.id = id;
        this.name = name;
        this.telNo = telNo;
        this.age = age;
    }
}
