package com.harshil.multiplefilestoaws.repository;


import com.harshil.multiplefilestoaws.domain.MediaDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleUploadRepository extends CrudRepository<MediaDTO, Long>{
    MediaDTO findById (String Id);
}
