package com.alkemy.java.service;

import org.springframework.web.multipart.MultipartFile;

public interface BASE64DecodingAndEncodingService {
    MultipartFile base64ToMultipart(String base64);
}
