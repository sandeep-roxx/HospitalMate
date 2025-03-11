package com.verma.sandeep.hospital.mate.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3FileService {
	
	@Value("${aws.s3.bucket-name}")
    private String bucketName;
	@Value("${aws.access-key}") 
	private String accessKey;
    @Value("${aws.secret-key}") 
    private String secretKey;
    @Value("${aws.s3.region}")
    private String region;
    
    private final S3Client s3Client;
    
    public S3FileService() {
        this.s3Client = S3Client.builder().region(Region.of(region))
        		                                                     .credentialsProvider(StaticCredentialsProvider
        		                                                    .create(AwsBasicCredentials.create(accessKey, secretKey)))
        		                                                     .build();    		
    }
    
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        String fileName = folder + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();
        
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
        
        return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
    }

}
