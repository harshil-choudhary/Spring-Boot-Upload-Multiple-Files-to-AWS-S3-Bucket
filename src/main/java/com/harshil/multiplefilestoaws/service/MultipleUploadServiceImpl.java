package com.harshil.multiplefilestoaws.service;

import com.harshil.multiplefilestoaws.domain.ListOfMediaDTO;
import com.harshil.multiplefilestoaws.domain.MediaDTO;
//import com.harshil.multiplefilestoaws.repository.MultipleUploadRepository;
import com.harshil.multiplefilestoaws.exception.BadRequestException;
import com.harshil.multiplefilestoaws.exception.FileSizeException;
import com.harshil.multiplefilestoaws.exception.FileTypeDetectionException;
import com.harshil.multiplefilestoaws.repository.SingleUploadRepository;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class MultipleUploadServiceImpl implements MultipleUploadService{

    @Autowired
    FileProcessingService fileProcessingService;

    @Autowired
    SingleUploadRepository singleUploadRepository;

    @Value("${fileNumberLimit}")
    private int fileNumberLimit;

    @Value("${totalFileSizeLimit}")
    private long totalFileSizeLimit;

    @Override
    public ListOfMediaDTO uploadFiles(String entityIdentifier, String userId, MultipartFile[] files) {

        if (files.length > fileNumberLimit) {
            throw new FileSizeException("Cannot upload more than 5 files");
        }

        long totalFileSize = Arrays.stream(files).mapToLong(MultipartFile::getSize).sum();

        if (totalFileSize > totalFileSizeLimit) {
            throw new FileSizeException("Cannot upload more than 100 MB of files");
        }
        AtomicInteger fileCounter = new AtomicInteger();
        LocalTime localTime = LocalTime.now(ZoneId.of("GMT+05:30"));
        List<MediaDTO> mediaDTOList = new ArrayList<>();

        Arrays.stream(files).forEach(file -> {

            if (file.isEmpty()) {
                throw new BadRequestException("Cannot upload empty file");
            }

            Tika tika = new Tika();
            String fileTypeWithExtension;
            String fileType;
            try {
                fileTypeWithExtension = tika.detect(file.getBytes());
            }
            catch (IOException e) {
                throw new FileTypeDetectionException("Failed to obtain file type");
            }
            fileType = fileTypeWithExtension.substring(0, fileTypeWithExtension.lastIndexOf("/"));

            fileCounter.set(fileCounter.get() + 1);
            String fileName = fileType
                    + "_" + entityIdentifier
                    + "_" + userId
                    + "_" + localTime
                    + "_" + fileCounter;

            System.out.println("\n" + fileName);

            MediaDTO mediaDTO = fileProcessingService.fileValidator(file, fileName, fileType, entityIdentifier, userId);
            singleUploadRepository.save(mediaDTO);
            mediaDTOList.add(singleUploadRepository.findById(mediaDTO.getId()));
            System.out.println(mediaDTOList);
        });
        String requestId = UUID.randomUUID().toString();
        ListOfMediaDTO listOfMediaDTO = ListOfMediaDTO.builder()
                .requestId(requestId)
                .mediaDTOList(mediaDTOList)
                .build();

        return listOfMediaDTO;
    }
}
