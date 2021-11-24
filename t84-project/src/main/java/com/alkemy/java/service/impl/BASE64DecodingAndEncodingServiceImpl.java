package com.alkemy.java.service.impl;

import com.alkemy.java.exceptions.BASE64ImageFormatException;
import com.alkemy.java.model.BASE64DecodedMultipartFile;
import com.alkemy.java.service.BASE64DecodingAndEncodingService;
import com.alkemy.java.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import static java.util.Locale.getDefault;

@Service
public class BASE64DecodingAndEncodingServiceImpl implements BASE64DecodingAndEncodingService {

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public MultipartFile base64ToMultipart(String base64) {

        String[] baseStrs = base64.split(",");
        byte[] b = new byte[0];

        try{
            b = Base64.getDecoder().decode(baseStrs[1]);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            throw new BASE64ImageFormatException(messageUtil
                    .getMessage("exception.invalidImage",null,getDefault()));
        }
        return new BASE64DecodedMultipartFile(b, baseStrs[0]);
    }
}
