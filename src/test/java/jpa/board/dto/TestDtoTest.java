package jpa.board.dto;

import jpa.board.entity.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : jpa.board.dto
 * fileName       : TestDtoTest
 * author         : 김재성
 * date           : 2022-08-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-09        김재성       최초 생성
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestDtoTest {

    TestDto testDto = new TestDto(1L);
    TestDto testDto1 = new TestDto(1L, "이름");
    TestDto testDto2 = new TestDto(1L, "이름", "번호");
    TestDto testDto3 = new TestDto(1L, "이름", "번호", 20);

    TestDto1 testDtoo = TestDto1.builder()
                        .name("이름")
                        .age(20)
                        .build();

    Test test = testDtoo.toEntity();

}