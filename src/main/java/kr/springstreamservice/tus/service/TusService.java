package kr.springstreamservice.tus.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

public interface TusService {
    public String tusUpload(HttpServletRequest request, HttpServletResponse response);
    public void createFile(InputStream inputStream, String fileName) throws IOException;
}
