package com.synergy.productService.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BannerModel {
    private String bannerName;
    private MultipartFile bannerImage;
}
