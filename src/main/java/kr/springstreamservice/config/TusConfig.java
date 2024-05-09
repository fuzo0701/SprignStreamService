package kr.springstreamservice.config;

import me.desair.tus.server.TusFileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TusConfig {
    @Value("${tus.data.path}")
    private String tusDataPath;

    @Value("${tus.data.expiration}")
    Long tusDataExpiration;

    @Bean
    public TusFileUploadService tus(){
        return new TusFileUploadService()
                .withStoragePath(tusDataPath)
                .withDownloadFeature()
                .withUploadExpirationPeriod(tusDataExpiration)
                .withThreadLocalCache(true)
                .withUploadUri("/tus/upload");
    }
}
