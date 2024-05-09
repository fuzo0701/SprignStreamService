package kr.springstreamservice.basic.service.impl;

import kr.springstreamservice.basic.service.BasicService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BasicServiceImpl implements BasicService {

    @Override
    public void saveFile(MultipartFile file) {
        if(!file.isEmpty()) {
            Path filePath = Paths.get("video", file.getOriginalFilename());

            try (OutputStream os = Files.newOutputStream(filePath)){
                os.write(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
