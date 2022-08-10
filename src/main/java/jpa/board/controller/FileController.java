package jpa.board.controller;

import jpa.board.entity.File;
import jpa.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * packageName    : jpa.board.controller
 * fileName       : FileController
 * author         : 김재성
 * date           : 2022-08-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-10        김재성       최초 생성
 */

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;

    @GetMapping(value = {"/fileDownload/{fileIdx}"})
    @ResponseBody
    public void downloadFile(HttpServletResponse res, @PathVariable Long fileIdx) throws UnsupportedEncodingException {

        //파일 조회
        File fileInfo = fileRepository.findById(fileIdx).get();

        //파일 경로
        Path saveFilePath = Paths.get(fileInfo.getUploadDir() + java.io.File.separator + fileInfo.getSavedFileName());

        //해당 경로에 파일이 없으면
        if(!saveFilePath.toFile().exists()) {
            throw new RuntimeException("file not found");
        }

        //파일 헤더 설정
        setFileHeader(res, fileInfo);

        //파일 복사
        fileCopy(res, saveFilePath);
    }

    /**
     * 파일 header 설정
     * @param res
     * @param fileInfo
     * @throws UnsupportedEncodingException
     */
    private void setFileHeader(HttpServletResponse res, File fileInfo) throws UnsupportedEncodingException {
        res.setHeader("Content-Disposition", "attachment; filename=\"" +  URLEncoder.encode((String) fileInfo.getOriginFileName(), "UTF-8") + "\";");
        res.setHeader("Content-Transfer-Encoding", "binary");
        res.setHeader("Content-Type", "application/download; utf-8");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
    }

    /**
     * 파일 복사
     * @param res
     * @param saveFilePath
     */
    private void fileCopy(HttpServletResponse res, Path saveFilePath) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(saveFilePath.toFile());
            FileCopyUtils.copy(fis, res.getOutputStream());
            res.getOutputStream().flush();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                fis.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
