package com.synergy.productService.service;

import com.synergy.productService.dto.KostModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface KostService {

    String uploadFrontBuildingPhoto(MultipartFile file) throws IOException;
    String uploadFrontRoadPhoto(MultipartFile file) throws IOException;
    String uploadFrontFarbuildingPhoto(MultipartFile file) throws IOException;

    Map<String, Object> getKostByProfileId(Long profileIf);

}
