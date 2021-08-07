package com.harshil.multiplefilestoaws.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
//@Component
public class ListOfMediaDTO {
    @Id
    private String requestId;
    private List<MediaDTO> mediaDTOList;
}
