package com.harshil.multiplefilestoaws.service;

import com.harshil.multiplefilestoaws.domain.ListOfMediaDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MultipleUploadService {
    ListOfMediaDTO uploadFiles(String entityIdentifier, String userId, MultipartFile[] files);
}
