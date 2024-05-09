package kr.springstreamservice.chunkBasic.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ChunkBasicUploadService {
    public boolean chunkBasicUpload(MultipartFile file, int chunkNumber, int totalChunks) throws IOException;
}
