package kr.springstreamservice.hls.service.impl;

import kr.springstreamservice.hls.service.HlsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class HlsServiceImpl implements HlsService {

    @Value("${tus.output.path.hls}")
    private String outputPath;

    @Override
    public File getHlsFile(String key, String filename) {
        return new File(outputPath + "/" + key + "/" + filename);
    }
}
