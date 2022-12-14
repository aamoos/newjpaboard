package jpa.board.repositoryImpl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpa.board.dto.BoardDto;

import jpa.board.dto.BoardFileDto;
import jpa.board.dto.QBoardDto;
import jpa.board.dto.QBoardFileDto;
import jpa.board.entity.BoardFile;
import jpa.board.repository.CustomBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

import static jpa.board.entity.QBoard.board;
import static jpa.board.entity.QBoardFile.boardFile;
import static jpa.board.entity.QMember.member;

/**
 * packageName    : jpa.board.repositoryImpl
 * fileName       : BoardRepositoryImpl
 * author         : 김재성
 * date           : 2022-08-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-02        김재성       최초 생성
 */
@Repository
public class BoardRepositoryImpl implements CustomBoardRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
    * @methodName : selectBoardList
    * @date : 2022-08-10 오후 4:13
    * @author : 김재성
    * @Description: 게시판 목록
    **/
    @Override
    public Page<BoardDto> selectBoardList(String searchVal, Pageable pageable) {
        List<BoardDto> content = getBoardMemberDtos(searchVal, pageable);
        Long count = getCount(searchVal);
        return new PageImpl<>(content, pageable, count);
    }

    /**
    * @methodName : selectBoardDetail
    * @date : 2022-08-10 오후 4:13
    * @author : 김재성
    * @Description: 게시판 첨부파일 리스트
    **/
    @Override
    public List<BoardFileDto> selectBoardFileDetail(Long boardId) {
        List<BoardFileDto> content = jpaQueryFactory
                .select(new QBoardFileDto(
                         boardFile.id
                        ,boardFile.file.id
                        ,boardFile.file.originFileName
                        ,boardFile.file.size
                        ,boardFile.file.extension
                ))
                .from(boardFile)
                .leftJoin(boardFile.file)
                .where(boardFile.boardId.eq(boardId))
                .where(boardFile.delYn.eq("N"))
                .fetch();
        return content;
    }

    /**
    * @methodName : getCount
    * @date : 2022-08-10 오후 4:13
    * @author : 김재성
    * @Description: 게시판 페이징 count
    **/
    private Long getCount(String searchVal){
        Long count = jpaQueryFactory
                .select(board.count())
                .from(board)
                .where(containsSearch(searchVal))
                //.leftJoin(board.member, member)   //검색조건 최적화
                .fetchOne();
        return count;
    }

    /**
    * @methodName : getBoardMemberDtos
    * @date : 2022-08-10 오후 4:13
    * @author : 김재성
    * @Description: 게시판 페이징 목록
    **/
    private List<BoardDto> getBoardMemberDtos(String searchVal, Pageable pageable){
        List<BoardDto> content = jpaQueryFactory
                .select(new QBoardDto(
                        board.id
                        ,board.title
                        ,board.content
                        ,board.regDate
                        ,board.uptDate
                        ,board.viewCount
                        ,member.username))
                .from(board)
                .leftJoin(board.member, member)
                .where(containsSearch(searchVal))
                .where(board.delYn.eq("N"))
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return content;
    }

    /**
     * @methodName : containsSearch
     * @date : 2022-08-02 오후 5:28
     * @author : 김재성
     * @Description: %키워드% 조회
     **/
    private BooleanExpression containsSearch(String searchVal){
        return searchVal != null ? board.title.contains(searchVal) : null;
    }
}
