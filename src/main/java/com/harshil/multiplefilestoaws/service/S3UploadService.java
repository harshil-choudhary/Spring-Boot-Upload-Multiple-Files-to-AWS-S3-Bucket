package com.harshil.multiplefilestoaws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.harshil.multiplefilestoaws.config.AmazonS3Config;
import com.harshil.multiplefilestoaws.domain.MediaDTO;
import com.harshil.multiplefilestoaws.exception.AWSUploadException;
import com.harshil.multiplefilestoaws.exception.FileTypeDetectionException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class S3UploadService {

    private AmazonS3 amazonS3;

    AmazonS3Config amazonS3Config;

    public MediaDTO uploadFile(MultipartFile file, String fileName, String fileType, String entityIdentifier, String userId) {

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", fileType);
        metadata.put("Content-Length",String.valueOf(file.getSize()));

        String path = amazonS3Config.getBucketName() + "/" + amazonS3Config.getAwsFolder();

        try {
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            Optional<Map<String, String>> optionalMetaData = Optional.of(metadata);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            optionalMetaData.ifPresent(map -> {
                if (!map.isEmpty()) {
                    map.forEach(objectMetadata::addUserMetadata);
                }
            });
            amazonS3.putObject(new PutObjectRequest(path, fileName, byteArrayInputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
        throw new AWSUploadException("Failed to Upload file");
        }

        URL s3URL = amazonS3.getUrl(path ,fileName);
        System.out.println(s3URL);

        MediaDTO mediaDTO = MediaDTO.builder()
                .id(fileName)
                .entityIdentifier(entityIdentifier)
                .userId(userId)
                .url(s3URL)
                .type(fileType)
                .build();

        return mediaDTO;
    }
}
