package com.synergy.productService.dto;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Data
public class KostModel {

    private Long profileId;
    private String name;
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    private String pic;
    @Type(type = "org.hibernate.type.TextType")
    private String additionalNotes;
    private String picPhoneNumber;

//    private MultipartFile frontBuildingPhoto;
//    private MultipartFile frontRoadPhoto;
//    private MultipartFile frontFarbuildingPhoto;

    private String province;
    private String city;

    private String address;
    private String gmaps;
    @Type(type = "org.hibernate.type.TextType")
    private String locationAdditionalNotes;

    private Boolean electric;

    private Boolean water;

    private Boolean wifi;

    private Boolean laundry;

    private Boolean refrigerator;

    private Boolean dispenser;

    private String sizeRoom;

    private Boolean insideBathroom;

}
