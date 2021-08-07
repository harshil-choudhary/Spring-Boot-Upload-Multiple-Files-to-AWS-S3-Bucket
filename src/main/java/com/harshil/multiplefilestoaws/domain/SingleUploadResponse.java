package com.harshil.multiplefilestoaws.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Component
public class SingleUploadResponse {
    @Id
    @GeneratedValue
    private long id;
    private String entityIdentifier;
    private String userId;
    private URL url;
    private String type;
}
