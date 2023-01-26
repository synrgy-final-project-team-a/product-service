package com.synergy.productService.controller;

import com.synergy.productService.dto.KostModel;
import com.synergy.productService.dto.RoomModel;
import com.synergy.productService.entity.*;
import com.synergy.productService.repository.*;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/tennant")
public class KostTennantController {

    @Autowired
    private KostServiceImpl kostServiceImpl;

    @Autowired
    public Response res;

    @Autowired
    private KostRepo kostRepo;

    @Autowired
    private RuleRepo ruleRepo;

    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private KostRuleRepo kostRuleRepo;
    @Autowired
    private FacilityRepo facilityRepo;
    @Autowired
    private RoomRepo roomRepo;

    @PostMapping("/kost/user/{profileId}")
    public ResponseEntity<Map<String, Object>> createKost(
            @PathVariable("profileId") Long profileId,
            @ModelAttribute KostModel kost
            ) throws IOException {
        try {
            Kost kostInstance = new Kost();

            kostInstance.setName(kost.getName());
            kostInstance.setDescription(kost.getDescription());
            kostInstance.setPic(kost.getPic());
            kostInstance.setPicPhoneNumber(kost.getPicPhoneNumber());
            kostInstance.setAdditionalNotes(kost.getAdditionalNotes());
            kostInstance.setProvince(kost.getProvince());
            kostInstance.setCity(kost.getCity());
            kostInstance.setAddress(kost.getAddress());
            kostInstance.setGmaps(kost.getGmaps());
            kostInstance.setLocationAdditionalNotes(kost.getLocationAdditionalNotes());
            kostInstance.setAvailableRoom(kost.getAvailableRoom());
            kostInstance.setEnabled(kost.getEnabled());

            // Assign general facility
            kostInstance.setTv(kost.getTv());
            kostInstance.setElectric(kost.getElectric());
            kostInstance.setLaundry(kost.getLaundry());
            kostInstance.setRefrigerator(kost.getRefrigerator());
            kostInstance.setWater(kost.getWater());
            kostInstance.setWifi(kost.getWifi());
            kostInstance.setDrying_ground(kost.getDrying_ground());
            kostInstance.setKitchen(kost.getKitchen());
            kostInstance.setLivingRoom(kost.getLivingRoom());
            kostInstance.setParking(kost.getParking());

            // From file upload form
            kostInstance.setFrontBuildingPhoto(kostServiceImpl.uploadFrontBuildingPhoto(kost.getFrontBuildingPhoto()));
            kostInstance.setFrontRoadPhoto(kostServiceImpl.uploadFrontRoadPhoto(kost.getFrontRoadPhoto()));
            kostInstance.setFrontFarbuildingPhoto(kostServiceImpl.uploadFrontFarbuildingPhoto(kost.getFrontFarbuildingPhoto()));

            // Add rule to kost
            List<Rule> rules = new ArrayList<>();
            for (String id : kost.getRuleList().split(",")) {
                rules.add(ruleRepo.findById(Long.parseLong(id)).get());
            }
            kostInstance.setRuleList(rules);

            // Assign the new instance kost into current user
            Optional<Profile> user = profileRepo.findById(profileId);

            user.ifPresent(kostInstance::setProfile);

            Kost kostCreated = kostRepo.save(kostInstance);

            return new ResponseEntity<>(res.resSuccess(kostCreated, "success", 201), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(res.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/kost/{id}")
    public ResponseEntity<Map<String, Object>> deleteKostById(@PathVariable Long id) {
        try{
            Optional<Kost> kost = kostRepo.findById(id);

            if (kost.isPresent()) {
                kost.get().setDeletedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
                Kost kostDeleted = kostRepo.save(kost.get());
                return new ResponseEntity<>(res.resSuccess(kostDeleted, "success", 200), HttpStatus.OK);
            }

            return new ResponseEntity<>(res.notFoundError("kost doesn't exist"), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(res.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/kost/{id}")
    public ResponseEntity<Map<String, Object>> editKost(
            @PathVariable("id") Long kostId,
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
            @RequestParam("ruleId") String ruleId
    ) throws IOException {

        try {
            Kost kost = kostRepo.checkExistingKostId(kostId);
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

            // delete old rule
            kostRuleRepo.deleteRuleById(kostRepo.findById(kostId).get().getId());

            List<Rule> rules = new ArrayList<>();
            for (String idRule : ruleId.split(",")) {
                rules.add(ruleRepo.findById(Long.parseLong(idRule)).get());
            }

            kost.setRuleList(rules);
            kost.setFrontBuildingPhoto(kostServiceImpl.uploadFrontBuildingPhoto(file1));
            kost.setFrontRoadPhoto(kostServiceImpl.uploadFrontRoadPhoto(file2));
            kost.setFrontFarbuildingPhoto(kostServiceImpl.uploadFrontFarbuildingPhoto(file3));

            Kost obj = kostRepo.save(kost);

            return new ResponseEntity<>(res.resSuccess(obj, "Success edit kost!", 200), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(res.clientError("Failed edit kost!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/room/{kostId}")
    public ResponseEntity<Map<String, Object>> createRoom(
            @PathVariable(value = "kostId") Long kostId,
            @ModelAttribute RoomModel room
            ) throws IOException {
        try{
            Optional<Kost> kost = kostRepo.findById(kostId);

            if(!kost.isPresent()){
                return new ResponseEntity<>(res.clientError("kost doesn't exist"), HttpStatus.BAD_REQUEST);
            }

            Room roomInstance = new Room();
            roomInstance.setQuantityRoom(room.getQuantityRoom());
            roomInstance.setKostTypeMan(room.getKostTypeMan());
            roomInstance.setKostTypeWoman(room.getKostTypeWoman());
            roomInstance.setKostTypeMixed(room.getKostTypeMixed());
            roomInstance.setSizeRoom(room.getSizeRoom());
            roomInstance.setEnabled(room.getEnabled());

            // From file upload form
            roomInstance.setFrontRoomPhoto(kostServiceImpl.uploadFile(room.getFrontRoomPhoto(), "from_room_photo"));
            roomInstance.setInsideRoomPhoto(kostServiceImpl.uploadFile(room.getInsideRoomPhoto(), "inside_room_photo"));
            roomInstance.setBathroomPhoto(kostServiceImpl.uploadFile(room.getBathroomPhoto(), "bathroom_photo"));
            roomInstance.setOtherRoomPhoto(kostServiceImpl.uploadFile(room.getOtherRoomPhoto(), "other_room_photo"));

            // Create facility for room
            Facility facilityInstance = new Facility();
            facilityInstance.setAc(room.getAc());
            facilityInstance.setBlanket(room.getBlanket());
            facilityInstance.setFan(room.getFan());
            facilityInstance.setFurniture(room.getFurniture());
            facilityInstance.setShower(room.getShower());
            facilityInstance.setSittingCloset(room.getSittingCloset());
            facilityInstance.setSpringBed(room.getSpringBed());
            facilityInstance.setTableLearning(room.getTableLearning());
            facilityInstance.setWaterHeater(room.getWaterHeater());
            facilityInstance.setInsideBathroom(room.getInsideBathroom());
            facilityInstance.setNonSittingCloset(room.getNonSittingCloset());
            facilityInstance.setOutsideBathroom(room.getOutsideBathroom());
            facilityInstance.setWindows(room.getWindows());
            facilityInstance.setBathtub(room.getBathtub());
            facilityInstance.setRoomTv(room.getRoomTv());
            facilityInstance.setDispenser(room.getDispenser());

            Facility facilityRoomCreated = facilityRepo.save(facilityInstance);

            // Assign facility that has been created to room
            roomInstance.setFacility(facilityRoomCreated);

            // Attach room into current kost
            roomInstance.setKost(kost.get());

            // Save kost
            Room roomCreated = roomRepo.save(roomInstance);

            return new ResponseEntity<>(res.resSuccess(roomCreated, "success", 201), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(res.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/kost/user/{id}"})
    public ResponseEntity<Map<String, Object>> getKostByProfileId(
            @PathVariable(value = "id") Long profileId
    ){
        return new ResponseEntity<>(kostServiceImpl.getKostByProfileId(profileId), HttpStatus.OK);
    }

}
