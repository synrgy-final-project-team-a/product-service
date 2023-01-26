package com.synergy.productService.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
public class RoomModel {
    private MultipartFile frontRoomPhoto;

    private MultipartFile insideRoomPhoto;

    private MultipartFile bathroomPhoto;

    private MultipartFile otherRoomPhoto;

    private Integer quantityRoom;

    private Boolean kostTypeMan;

    private Boolean kostTypeWoman;

    private Boolean kostTypeMixed;

    private String sizeRoom;

    private Boolean enabled = true;

    private Boolean ac;

    private Boolean blanket;

    private Boolean fan;

    private Boolean furniture;

    private Boolean shower;

    private Boolean sittingCloset;

    private Boolean springBed;

    private Boolean tableLearning;

    private Boolean waterHeater;

    private Boolean insideBathroom;

    private Boolean nonSittingCloset;

    private Boolean outsideBathroom;

    private Boolean windows;

    private Boolean bathtub;

    private Boolean roomTv;

    private Boolean dispenser;

}
