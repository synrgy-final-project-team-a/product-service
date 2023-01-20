package com.synergy.productService.service;

import com.synergy.productService.dto.KostModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface KostService {

    public String uploadFrontBuildingPhoto(MultipartFile file) throws IOException;
    public String uploadFrontRoadPhoto(MultipartFile file) throws IOException;
    public String uploadFrontFarbuildingPhoto(MultipartFile file) throws IOException;

}
