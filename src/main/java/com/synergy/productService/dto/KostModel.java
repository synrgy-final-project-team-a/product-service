package com.synergy.productService.dto;

import com.synergy.productService.entity.Facility;
import com.synergy.productService.entity.KostPhoto;
import com.synergy.productService.entity.Location;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class KostModel {
    private String name;
    private String roomType;
    private String kostType;
    private String province;
    private String city;
    private String address;
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    private String pic;
    private String picPhoneNumber;
    @Type(type = "org.hibernate.type.TextType")
    private String kostAdditionalNotes;
    private String gmaps;
    @Type(type = "org.hibernate.type.TextType")
    private String locationAdditionalNotes;
    private File insideRoom;
}
