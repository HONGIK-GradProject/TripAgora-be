package com.example.TripAgora.common.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    // 이 메서드가 반환하는 객체를 Spring Bean으로 등록합니다.
    @Bean
    public AmazonS3 amazonS3Client() {
        // 1. 액세스 키와 시크릿 키로 자격 증명(Credentials) 객체를 만듭니다.
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // 2. 자격 증명과 리전 정보로 AmazonS3 클라이언트 객체를 빌드하여 반환합니다.
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }
}