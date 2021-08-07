package com.harshil.multiplefilestoaws.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    @Value("${awsAccessKey}")
    private String accessKey;

    @Value("${awsSecretKey}")
    private String secretKey;

    @Value("${awsRegion}")
    private String region;

    @Value("${awsBucketName}")
    private String bucketName;

    @Value("${awsFolder}")
    private String awsFolder;

    public String getBucketName() {
        return bucketName;
    }

    public String getAwsFolder() {
        return awsFolder;
    }

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}