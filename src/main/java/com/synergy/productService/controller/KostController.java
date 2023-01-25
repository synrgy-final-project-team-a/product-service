package com.synergy.productService.controller;

import com.synergy.productService.entity.Kost;
import com.synergy.productService.entity.Profile;
import com.synergy.productService.entity.Rule;
import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.repository.KostRuleRepo;
import com.synergy.productService.repository.ProfileRepo;
import com.synergy.productService.repository.RuleRepo;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.util.*;

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
    private RuleRepo ruleRepo;

    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private KostRuleRepo kostRuleRepo;

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
            @RequestParam("tv") Boolean tv,
            @RequestParam("kitchen") Boolean kitchen,
            @RequestParam("parking") Boolean parking,
            @RequestParam("dryingGround") Boolean dryingGround,
            @RequestParam("livingRoom") Boolean livingRoom,
            @RequestParam("profileId") Long profileId,
            @RequestParam("ruleId") String ruleId
    ) throws IOException {

        try {
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
            kost.setTv(tv);
            kost.setKitchen(kitchen);
            kost.setParking(parking);
            kost.setDryingGround(dryingGround);
            kost.setLivingRoom(livingRoom);

            //        add rule to kost
            List<Rule> rules = new ArrayList<>();
            for (String id : ruleId.split(",")) {
                rules.add(ruleRepo.findById(Long.parseLong(id)).get());
            }
            kost.setRuleList(rules);


            Profile profile = profileRepo.checkExistingProfileId(profileId);
            kost.setProfile(profile);
            kost.setFrontBuildingPhoto(kostServiceImpl.uploadFrontBuildingPhoto(file1));
            kost.setFrontRoadPhoto(kostServiceImpl.uploadFrontRoadPhoto(file2));
            kost.setFrontFarbuildingPhoto(kostServiceImpl.uploadFrontFarbuildingPhoto(file3));

            Kost obj = kostRepo.save(kost);

            return new ResponseEntity<Map>(response.resSuccess(obj, "Success add kost!", 201), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<Map>(response.clientError("Failed add kost!"), HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/kost/delete/{id}")
    public ResponseEntity<Map> deleteKostById(@PathVariable Long id) {
        Kost kost = kostRepo.checkExistingKostId(id);

        // implement soft delete by set DeletedAt
        kost.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        kostRepo.save(kost);
        Kost obj = kostRepo.checkExistingKostId(id);

        return new ResponseEntity<Map>(response.resSuccess(obj, "Success delete kost!", 200), HttpStatus.OK);
    }

    @PutMapping("/kost/edit")
    public ResponseEntity<Map> editKost(
            @RequestParam("id") Long id,
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
            @RequestParam("tv") Boolean tv,
            @RequestParam("kitchen") Boolean kitchen,
            @RequestParam("parking") Boolean parking,
            @RequestParam("dryingGround") Boolean dryingGround,
            @RequestParam("livingRoom") Boolean livingRoom,
            @RequestParam("ruleId") String ruleId
    ) throws IOException {

        try {
            Kost kost = kostRepo.checkExistingKostId(id);
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
            kost.setTv(tv);
            kost.setKitchen(kitchen);
            kost.setParking(parking);
            kost.setDryingGround(dryingGround);
            kost.setLivingRoom(livingRoom);

//       delete old rule and add new rule
            kostRuleRepo.deleteRuleById(kostRepo.findById(id).get().getId());
            List<Rule> rules = new ArrayList<>();
            for (String idRule : ruleId.split(",")) {
                rules.add(ruleRepo.findById(Long.parseLong(idRule)).get());
            }
            kost.setRuleList(rules);
            kost.setFrontBuildingPhoto(kostServiceImpl.uploadFrontBuildingPhoto(file1));
            kost.setFrontRoadPhoto(kostServiceImpl.uploadFrontRoadPhoto(file2));
            kost.setFrontFarbuildingPhoto(kostServiceImpl.uploadFrontFarbuildingPhoto(file3));

            Kost obj = kostRepo.save(kost);

            return new ResponseEntity<Map>(response.resSuccess(obj, "Success edit kost!", 200), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Map>(response.clientError("Failed edit kost!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = {"/kost/get/{id}"})
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.getById(id), HttpStatus.OK);
    }

}
