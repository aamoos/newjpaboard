package jpa.board.repository;

import jpa.board.dto.BoardDto;
import jpa.board.dto.BoardFileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * packageName    : jpa.board.repository
 * fileName       : CustomBoardRepository
 * author         : 김재성
 * date           : 2022-08-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-02        김재성       최초 생성
 */
public interface CustomBoardRepository {

    /**
    * @methodName : selectBoardList
    * @date : 2022-08-10 오후 3:46
    * @author : 김재성
    * @Description: 게시판 페이징 목록
    **/
    Page<BoardDto> selectBoardList(String searchVal, Pageable pageable);

    /**
    * @methodName : selectBoardDetail
    * @date : 2022-08-10 오후 3:46
    * @author : 김재성
    * @Description: 게시판 상세 첨부파일 조회
    **/
    List<BoardFileDto> selectBoardFileDetail(Long boardId);
}
