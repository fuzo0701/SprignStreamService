package kr.springstreamservice.chunk.service.impl;

import kr.springstreamservice.chunk.service.ChunkUploadService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ChunkUploadServiceImpl implements ChunkUploadService {
    private static final Logger log = LoggerFactory.getLogger(ChunkUploadServiceImpl.class);

    @Override
    public boolean chunkUpload(MultipartFile file, int chunkNumber, int totalChunks, String key) throws IOException {
        String uploadDir = "video";
        String tempDir = "video/" + key;

        File dir = new File(tempDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filename = file.getOriginalFilename() + ".part" + chunkNumber;
        Path filePath = Paths.get(tempDir, filename);
        Files.write(filePath, file.getBytes());

        if(chunkNumber == totalChunks - 1) {
            String[] split = file.getOriginalFilename().split("\\.");
            String outputFileName = UUID.randomUUID() + "." + split[split.length - 1];
            Path outputFile = Paths.get(uploadDir, outputFileName);
            Files.createFile(outputFile);
            for(int i = 0; i < totalChunks; i++) {
                Path chunkPath = Paths.get(tempDir, file.getOriginalFilename() + ".part" + i);
                Files.write(outputFile, Files.readAllBytes(chunkPath), StandardOpenOption.APPEND);
            }
            deleteDirectory(Paths.get(tempDir));
            log.info("File uploaded successfully");
            return true;
        } else {
            return false;
        }
    }

    private void deleteDirectory(Path directoryPath) {
        try (Stream<Path> walk = Files.walk(directoryPath)){
            walk.map(Path::toFile).forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getLastChunkNumber(String key) {
        Path temp = Paths.get("video", key);
        String [] list = temp.toFile().list();
        return list == null ? 0 : Math.max(list.length - 2, 0);
    }
}
