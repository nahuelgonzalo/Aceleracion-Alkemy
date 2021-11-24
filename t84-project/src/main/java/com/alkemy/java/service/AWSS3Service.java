package com.alkemy.java.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface AWSS3Service {
    public String uploadFile(MultipartFile file) throws IOException;
    public List<String> getObjectsOfs3();
    public InputStream downloadFile(String key);
    public HttpHeaders getHeaders(String key);
    public String deleteObject(String key);
    public boolean exist(String key);
    public String getKey(String url);
    public String setImageBase64(String fileBase64);
}
