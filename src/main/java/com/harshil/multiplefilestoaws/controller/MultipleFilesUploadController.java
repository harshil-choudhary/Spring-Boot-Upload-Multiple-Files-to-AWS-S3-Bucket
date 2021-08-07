package com.harshil.multiplefilestoaws.controller;

import com.harshil.multiplefilestoaws.domain.ListOfMediaDTO;
import com.harshil.multiplefilestoaws.service.MultipleUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping ("/file-upload")
public class MultipleFilesUploadController {

    MultipleUploadService multipleUploadService;

    @PostMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListOfMediaDTO> uploadFile(
            @RequestHeader("entity-identifier") String entityIdentifier,
            @RequestHeader("user-id") String userId,
            @RequestParam("files") MultipartFile[] files) {
        return new ResponseEntity<>(
                multipleUploadService.uploadFiles(entityIdentifier, userId, files), HttpStatus.OK
        );
    }

}