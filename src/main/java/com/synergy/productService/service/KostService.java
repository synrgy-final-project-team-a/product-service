package com.synergy.productService.service;

import com.synergy.productService.dto.FilterSortModel;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface KostService {

    String uploadFrontBuildingPhoto(MultipartFile file) throws IOException;
    String uploadFrontRoadPhoto(MultipartFile file) throws IOException;
    String uploadFrontFarbuildingPhoto(MultipartFile file) throws IOException;

    public Map getByIdSeeker(Long id);
     Map getByIdTennant(Long id);
     Map approveById(Long id);
    Map<String, List<Map<String,Object>>> getKostBySearch(String keyword);

     List<Map<String, Object>> getKostByFilterAndSort(FilterSortModel filterSortModel,
                                                      Pageable pageable);
     Map<String, List<Map<String, Object>>> getKostById(Long id);
}
