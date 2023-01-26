package com.synergy.productService.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class KostModel {
    private String name;
    private String description;
    private String pic;

    private String picPhoneNumber;

    private String additionalNotes;

    private MultipartFile frontBuildingPhoto;

    private MultipartFile frontRoadPhoto;

    private MultipartFile frontFarbuildingPhoto;

    private String province;

    private String city;

    private String address;

    private String gmaps;

    private String locationAdditionalNotes;

    private Integer availableRoom;

    private Boolean enabled = true;

    private Boolean tv;

    private Boolean electric;

    private Boolean laundry;

    private Boolean refrigerator;

    private Boolean water;

    private Boolean wifi;

    private Boolean drying_ground;

    private Boolean kitchen;

    private Boolean livingRoom;

    private Boolean parking;

    private String ruleList;

}
