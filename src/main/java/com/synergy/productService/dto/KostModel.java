package com.synergy.productService.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class KostModel {
    private String kostName;
    private String description;

    private Boolean kostTypeMan;
    private Boolean kostTypeWoman;
    private Boolean kostTypeMixed;
    private MultipartFile frontBuildingPhoto;

    private MultipartFile frontFarbuildingPhoto;
    private String yearSince;

    private String province;

    private String city;

    private String address;

    private String gmaps;
    private Boolean restrictedNight;
    private Boolean identityCard;
    private Boolean restrictedGender;
    private Boolean restrictedGuest;

    private Boolean maximumOne;

    private Boolean maximumTwo;
    private Boolean restrictedCheckout;

    private Boolean restrictedCheckin;

    private Boolean includeElectricity;

    private Boolean noSmoking;

    private Boolean enabled = false;

    private Boolean kostTv;

    private Boolean electric;

    private Boolean laundry;

    private Boolean refrigerator;

    private Boolean water;

    private Boolean wifi;
    private Boolean dispenser;


    private Boolean drying_ground;

    private Boolean kitchen;

    private Boolean livingRoom;

    private Boolean parkingMotorcycle;

    private Boolean parkingCar;


}
