package jpa.board.service;

import jpa.board.dto.BoardDto;
import jpa.board.dto.FileDto;
import jpa.board.entity.Board;
import jpa.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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

    @Transactional
    public Long saveFile(BoardDto boardDto) throws Exception {
        MultipartFile file = boardDto.get

        for(MultipartFile file1 : file) {


        }


        String fullPath = uploadDir + file.getOriginalFilename();
        file.transferTo(new File(fullPath));
        log.info("file.getOriginalFilename = {}", file.getOriginalFilename());
        log.info("fullPath = {}", fullPath);

        FileDto fileDto = FileDto.builder()
                .originFileName(file.getOriginalFilename())
                .fullPath(uploadDir + file.getOriginalFilename())
                .build();
        return fileRepository.save(fileDto.toEntity()).getId();
    }
}
