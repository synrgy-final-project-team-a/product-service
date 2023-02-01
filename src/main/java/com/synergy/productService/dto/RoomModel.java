package com.synergy.productService.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
public class RoomModel {

    private String roomName;

    private MultipartFile insideRoomPhoto;

    private MultipartFile otherRoomPhoto;

    private Integer quantityRoom;
    private Integer availableRoom;


    private String sizeRoom;

    private Boolean ac;

    private Boolean pillow;

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

    private Boolean chair;

    private Boolean roomTv;

}
