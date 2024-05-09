package kr.springstreamservice.basic.service;


import org.springframework.web.multipart.MultipartFile;

public interface BasicService {
    public void saveFile(MultipartFile file);
}
