package kr.springstreamservice.hls.service.impl;

import kr.springstreamservice.hls.service.HlsServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class HlsServiceImplV2 implements HlsServiceV2 {

    @Value("${tus.output.path.hls}")
    private String outputPath;

    @Override
    public File getHlsFileV2(String key, String resolution, String filename) {
        return new File(outputPath + "/" + key + "/"  + resolution + "/" + filename);
    }

    @Override
    public File getHlsFileV2(String key, String filename) {
        return new File(outputPath + "/" + key + "/" + filename);
    }
}
