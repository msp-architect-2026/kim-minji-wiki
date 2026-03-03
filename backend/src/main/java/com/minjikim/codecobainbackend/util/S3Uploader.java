package com.minjikim.codecobainbackend.util;

import com.minjikim.codecobainbackend.exception.FileProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class S3Uploader {
    private static final Logger log = LoggerFactory.getLogger(S3Uploader.class);

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile file, String dirName) {
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String storedFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String key = dirName + "/" + datePath + "/" + storedFilename;

        try {
            log.info("S3 업로드 시작: 원본 파일명 = {}", file.getOriginalFilename());
            log.info("S3 저장 경로 = {}", key);

            S3Client s3 = getS3Client();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(file.getContentType())
                    .acl(ObjectCannedACL.PUBLIC_READ) //음...
                    .build();

            s3.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            s3.close();

            String uploadedUrl = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
            log.info("✅ S3 업로드 성공: {}", uploadedUrl);

            return uploadedUrl;


        } catch (IOException e) {
            throw new FileProcessingException("S3 업로드 실패", e);
        }
    }

    private S3Client getS3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .build();
    }
}
