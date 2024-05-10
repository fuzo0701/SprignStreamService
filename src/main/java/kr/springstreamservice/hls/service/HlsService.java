package kr.springstreamservice.hls.service;

import java.io.File;

public interface HlsService {

    public File getHlsFile(String key, String filename);
}
