package com.harshil.multiplefilestoaws.service;

import com.harshil.multiplefilestoaws.domain.MediaDTO;
import com.harshil.multiplefilestoaws.exception.BadRequestException;
import com.harshil.multiplefilestoaws.exception.FileSizeException;
import lombok.AllArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileProcessingService {

    @Autowired
    S3UploadService s3UploadService;

    @Value("${imageSizeLimit}")
    private long imageSizeLimit;

    @Value("${videoSizeLimit}")
    private long videoSizeLimit;

    public MediaDTO fileValidator (MultipartFile file, String fileName, String fileType, String entityIdentifier, String userId) {

        if ((!fileType.equals("image")) && (!fileType.equals("video"))){
            throw new BadRequestException(fileName + " is not an Image or Video");
        }

        if (fileType.equals("image")) {
            return imageProcessor(file, fileName, fileType, entityIdentifier, userId);
        }

        if (fileType.equals("video")) {
            return videoProcessor(file, fileName, fileType, entityIdentifier, userId);
        }

        return null;
    }

    private MediaDTO imageProcessor (MultipartFile file, String fileName, String fileType, String entityIdentifier, String userId) {
        System.out.println(fileName + " is in image processor");
        if (file.getSize() > imageSizeLimit) {
            throw new FileSizeException(fileName + " is greater than 10 MB");
        }
        return s3UploadService.uploadFile(file, fileName, fileType, entityIdentifier, userId);
    }

    private MediaDTO videoProcessor (MultipartFile file, String fileName, String fileType, String entityIdentifier, String userId) {
        System.out.println(fileName + " is in video processor");
        if (file.getSize() > videoSizeLimit) {
            throw new FileSizeException(fileName + " is greater than 50 MB");
        }
        return s3UploadService.uploadFile(file, fileName, fileType, entityIdentifier, userId);
    }
}
