package com.harshil.multiplefilestoaws.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
public class MultipleUploadResponse{
    @Id
    @GeneratedValue
    private long id;
    private List<SingleUploadResponse> singleUploadResponseList;
}
