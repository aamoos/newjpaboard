package jpa.board.dto;

import jpa.board.entity.BoardFile;
import jpa.board.entity.Test;
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
public class TestDto1 {

    private Long id;
    private String name;
    private String telNo;
    private int age;

    public TestDto1(){

    }

    @Builder
    public TestDto1(Long id, String name, String telNo, int age){
        this.id = id;
        this.name = name;
        this.telNo = telNo;
        this.age = age;
    }

    public Test toEntity(){
        return Test.builder()
                .name(name)
                .telNo(telNo)
                .age(age)
                .build();
    }

}
