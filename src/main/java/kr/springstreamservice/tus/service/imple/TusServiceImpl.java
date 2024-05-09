package kr.springstreamservice.tus.service.imple;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.springstreamservice.tus.service.TusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TusServiceImpl implements TusService {

    private final TusFileUploadService tusFileUploadService;

    @Value("${tus.save.path}")
    private String savePath;

    @Override
    public String tusUpload(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 업로드
            tusFileUploadService.process(request, response);
            // 현재 업로드 정보
            UploadInfo uploadInfo = tusFileUploadService.getUploadInfo(request.getRequestURI());
            // 완료된 경우 파일 저장
            if(uploadInfo != null && !uploadInfo.isUploadInProgress()) {
                //파일저장
                createFile(tusFileUploadService.getUploadedBytes(request.getRequestURI()),uploadInfo.getFileName());
                // 임시파일 삭제
                tusFileUploadService.deleteUpload(request.getRequestURI());

                return "success";
            }

            return null;

        } catch (IOException | TusException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일업로드 (날짜별 디렉토리 하위에 저장)
    @Override
    public void createFile(InputStream inputStream, String fileName) throws IOException {
        LocalDate today = LocalDate.now();
        String uploadedPath = savePath + "/" + today;
        String vodName = getVodName(fileName);

        File file = new File(uploadedPath,vodName);
        FileUtils.copyInputStreamToFile(inputStream, file);
    }

    // 파일 이름은 랜덤 UUID 사용
    private String getVodName(String fileName) {
        String [] split = fileName.split("\\.");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid + "." + split[split.length - 1];
    }
}
