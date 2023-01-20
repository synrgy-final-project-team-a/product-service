package com.synergy.productService.controller;

import com.synergy.productService.dto.KostModel;
import com.synergy.productService.entity.Kost;
import com.synergy.productService.entity.Profile;
import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.repository.ProfileRepo;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tennant/")
public class KostController {

    @Autowired
    private KostServiceImpl kostServiceImpl;

    @Autowired
    public Response response;

    @Autowired
    private KostRepo kostRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @PostMapping("/kost/add")
    public ResponseEntity<Map> uploadImage(
            @RequestParam("frontBuildingPhoto") MultipartFile file1,
            @RequestParam("frontRoadPhoto") MultipartFile file2,
            @RequestParam("frontFarBuildingPhoto") MultipartFile file3,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("pic") String pic,
            @RequestParam("additionalNotes") String additionalNotes,
            @RequestParam("picPhoneNumber") String picPhoneNumber,
            @RequestParam("province") String province,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("gmaps") String gmaps,
            @RequestParam("locationAdditionalNotes") String locationAdditionalNotes,
            @RequestParam("electric") Boolean electric,
            @RequestParam("water") Boolean water,
            @RequestParam("wifi") Boolean wifi,
            @RequestParam("laundry") Boolean laundry,
            @RequestParam("refrigerator") Boolean refrigerator,
            @RequestParam("dispenser") Boolean dispenser,
            @RequestParam("sizeRoom") String sizeRoom,
            @RequestParam("insideBathroom") Boolean insideBathroom,
            @RequestParam("profileId") Long profileId) throws IOException {

        Kost kost = new Kost();
        kost.setName(name);
        kost.setDescription(description);
        kost.setPic(pic);
        kost.setAdditionalNotes(additionalNotes);
        kost.setPicPhoneNumber(picPhoneNumber);
        kost.setProvince(province);
        kost.setAddress(address);
        kost.setCity(city);
        kost.setGmaps(gmaps);
        kost.setLocationAdditionalNotes(locationAdditionalNotes);
        kost.setElectric(electric);
        kost.setWater(water);
        kost.setWifi(wifi);
        kost.setLaundry(laundry);
        kost.setRefrigerator(refrigerator);
        kost.setDispenser(dispenser);
        kost.setSizeRoom(sizeRoom);
        kost.setInsideBathroom(insideBathroom);

        Profile profile = profileRepo.checkExistingProfileId(profileId);
        kost.setProfile(profile);
        kost.setFrontBuildingPhoto(kostServiceImpl.uploadFrontBuildingPhoto(file1));
        kost.setFrontRoadPhoto(kostServiceImpl.uploadFrontRoadPhoto(file2));
        kost.setFrontFarbuildingPhoto(kostServiceImpl.uploadFrontFarbuildingPhoto(file3));

        Kost obj = kostRepo.save(kost);

        return new ResponseEntity<Map>(response.resSuccess(obj, "Success add kost!", 201), HttpStatus.CREATED);

    }
}

