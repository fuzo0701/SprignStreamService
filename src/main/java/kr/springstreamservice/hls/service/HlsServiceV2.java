package kr.springstreamservice.hls.service;

import java.io.File;

public interface HlsServiceV2 {

    public File getHlsFileV2(String key, String resolution, String filename);
    public File getHlsFileV2(String key, String filename);
}
