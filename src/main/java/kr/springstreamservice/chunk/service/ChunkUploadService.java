package kr.springstreamservice.chunk.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ChunkUploadService {
    public boolean chunkUpload(MultipartFile file, int chunkNumber, int totalChunks, String key) throws IOException;
    public int getLastChunkNumber(String key);
}
