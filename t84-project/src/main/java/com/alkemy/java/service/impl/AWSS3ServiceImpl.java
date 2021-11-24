package com.alkemy.java.service.impl;

import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.exceptions.UploadImageException;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.BASE64DecodingAndEncodingService;
import com.alkemy.java.util.MessageUtil;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.URL;
import java.util.*;
import static java.util.Locale.getDefault;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private MessageUtil messageUtil;

    @Value("${awwss3.pattern.key.regexp}")
    private String patterKey;

    @Autowired
    private BASE64DecodingAndEncodingService base64DecodingAndEncodingService;

    @Override
    public String uploadFile(MultipartFile file) throws IOException{
        URL url;
        try{
            String fileName = UUID.randomUUID().toString().concat("_").concat(file.getOriginalFilename());
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(file.getContentType());
            data.setContentLength(file.getSize());
            amazonS3.putObject(
                    new PutObjectRequest(bucketName, fileName, file.getInputStream(), data)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
            url = amazonS3.getUrl(bucketName, fileName);
        }catch (IOException ex){
            throw new IOException(ex.getLocalizedMessage());
        }catch (AmazonServiceException  ex ){
            throw new AmazonServiceException((messageUtil
                    .getMessage("exception.amazon_image_not_processed",null,getDefault())));
        }catch (SdkClientException ex){
            throw new SdkClientException(messageUtil
                    .getMessage("exception.amazon_connection",null,getDefault()));
        }
        return url.toString();
    }

    @Override
    public List<String> getObjectsOfs3() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map( item -> {
            return item.getKey();
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public InputStream downloadFile(String key) {
        S3Object headerOverrideObject = null;
        try{
            headerOverrideObject = amazonS3.getObject(bucketName,key);
        }catch (AmazonServiceException  e){
            throw new AmazonServiceException(messageUtil
                    .getMessage("exception.amazon_image_not_processed",null,getDefault()));
        }catch (SdkClientException  e){
            throw new SdkClientException(messageUtil
                    .getMessage("exception.amazon_connection",null,getDefault()));
        }
        return headerOverrideObject.getObjectContent();
    }

    @Override
    public HttpHeaders getHeaders(String key){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE,"image/*");
        return headers;
    }

    @Override
    public String deleteObject(String key) {
        if(exist(key)){
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName,key));
        }else{
            throw new NotFoundException(messageUtil.getMessage("exception.notFound",null, getDefault()));
        }
        return messageUtil.getMessage("amazon.delete_object",null, getDefault());
    }

    @Override
    public boolean exist(String key) {
        Optional<String> objectExist  = this.getObjectsOfs3().stream().filter(k -> k.contains(key)).findFirst();
        if(objectExist.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public String getKey(String url) {
        Pattern patron = Pattern.compile(patterKey);
        Matcher coincidences = patron.matcher(url);
        return coincidences.find() ? coincidences.group() : null;
    }

    @Override
    public String setImageBase64(String fileBase64) {
        if(fileBase64 == null || fileBase64.isEmpty())
            return null;
        try{
            return uploadFile(base64DecodingAndEncodingService.base64ToMultipart(fileBase64));
        }catch(IOException ex){
            throw new UploadImageException(
                    messageUtil.getMessage(
                            "error.upload_image",
                            null,
                            Locale.getDefault()));
        }catch(DataIntegrityViolationException ex){
            deleteObject(getKey(fileBase64));
            throw ex;
        }
    }

}
