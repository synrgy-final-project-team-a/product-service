package com.synergy.productService.service;

import com.synergy.productService.dto.FilterModel;
import com.synergy.productService.entity.Kost;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface KostService {

    String uploadFrontBuildingPhoto(MultipartFile file) throws IOException;
    String uploadFrontRoadPhoto(MultipartFile file) throws IOException;
    String uploadFrontFarbuildingPhoto(MultipartFile file) throws IOException;

//    Map<String, Object> getKostByProfileId(Long profileIf);

     Map getByIdTennant(Long id);
     Map getByIdSeeker(Long id);
     Map approveById(Long id);
    Map<String, List<Map<String,Object>>> getKostBySearch(String keyword);
    List<Map<String, Object>> getKostByArea(String province, String city, Pageable pageable);



     List<Map<String, Object>> getKostByFilter(FilterModel filterModel,
                                                Pageable pageable);
}
