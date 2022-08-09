package jpa.board.service;

import jpa.board.dto.BoardDto;
import jpa.board.dto.BoardFileDto;
import jpa.board.dto.FileDto;
import jpa.board.entity.BoardFile;
import jpa.board.entity.Member;
import jpa.board.repository.BoardFileRepository;
import jpa.board.repository.FileRepository;
import jpa.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * packageName    : jpa.board.service
 * fileName       : FileService
 * author         : 김재성
 * date           : 2022-08-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-05        김재성       최초 생성
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    @Value("${upload.path}")
    private String uploadDir;

    private final FileRepository fileRepository;

    private final BoardFileRepository boardFileRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Map<String, Object> saveFile(BoardDto boardDto, Long boardId) throws Exception {
        List<MultipartFile> multipartFile = boardDto.getMultipartFile();

        //결과 Map
        Map<String, Object> result = new HashMap<String, Object>();

        //파일 시퀀스 리스트
        List<Long> fileIds = new ArrayList<Long>();

        try {
            if (multipartFile != null) {
                if (multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
                    for (MultipartFile file1 : multipartFile) {
                        String originalFileName = file1.getOriginalFilename();    //오리지날 파일명
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
                        String savedFileName = UUID.randomUUID() + extension;    //저장될 파일 명

                        File targetFile = new File(uploadDir + savedFileName);

                        //초기값으로 fail 설정
                        result.put("result", "FAIL");

                        FileDto fileDto = FileDto.builder()
                                .originFileName(originalFileName)
                                .savedFileName(savedFileName)
                                .uploadDir(uploadDir)
                                .extension(extension)
                                .size(file1.getSize())
                                .contentType(file1.getContentType())
                                .build();
                        //파일 insert
                        Long fileId = insertFile(fileDto.toEntity());
                        log.info("fileId={}", fileId);

                        try {
                            InputStream fileStream = file1.getInputStream();
                            FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
                            //배열에 담기
                            fileIds.add(fileId);
                            result.put("fileIdxs", fileIds.toString());
                            result.put("result", "OK");
                        } catch (Exception e) {
                            //파일삭제
                            FileUtils.deleteQuietly(targetFile);    //저장된 현재 파일 삭제
                            e.printStackTrace();
                            result.put("result", "FAIL");
                            break;
                        }

                        BoardFileDto boardFileDto = BoardFileDto.builder()
                                .boardId(boardId)
                                .fileId(fileId)
                                .build();
                        insertBoardFile(boardFileDto.toEntity());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /** 파일 저장 db */
    @Transactional
    public Long insertFile(jpa.board.entity.File file) {
        return fileRepository.save(file).getId();
    }

    @Transactional
    public Long insertBoardFile(BoardFile boardFile) {
        return boardFileRepository.save(boardFile).getId();
    }

}
