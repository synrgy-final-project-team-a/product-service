package com.synergy.productService.controller;

import com.synergy.productService.dto.KostModel;
import com.synergy.productService.dto.RoomModel;
import com.synergy.productService.entity.*;
import com.synergy.productService.entity.enumeration.EDurationType;
import com.synergy.productService.repository.*;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@CrossOrigin("*")
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
    @Autowired
    private PriceRepo priceRepo;

    @PostMapping("/kost/user/{profileId}")
    public ResponseEntity<Map<String, Object>> createKost(
            @PathVariable("profileId") Long profileId,
            @ModelAttribute KostModel kost
            ) throws IOException {
        try {
            if((kostRepo.checkExistingProfileId(profileId)) > 2){
                return new ResponseEntity<>(res.clientError("Kost cannot be added more than 2!"), HttpStatus.BAD_REQUEST);
            }
            Kost kostInstance = new Kost();

            // Assign kost data
            kostInstance.setKostName(kost.getKostName());
            kostInstance.setDescription(kost.getDescription());
            kostInstance.setKostTypeMan(kost.getKostTypeMan());
            kostInstance.setKostTypeWoman(kost.getKostTypeWoman());
            kostInstance.setKostTypeMixed(kost.getKostTypeMixed());
            kostInstance.setYearSince(kost.getYearSince());
            kostInstance.setProvince(kost.getProvince());
            kostInstance.setCity(kost.getCity());
            kostInstance.setAddress(kost.getAddress());
            kostInstance.setGmaps(kost.getGmaps());
            kostInstance.setEnabled(kost.getEnabled());

            // Assign general facility
            kostInstance.setKostTv(kost.getKostTv());
            kostInstance.setElectric(kost.getElectric());
            kostInstance.setLaundry(kost.getLaundry());
            kostInstance.setRefrigerator(kost.getRefrigerator());
            kostInstance.setWater(kost.getWater());
            kostInstance.setWifi(kost.getWifi());
            kostInstance.setDrying_ground(kost.getDrying_ground());
            kostInstance.setKitchen(kost.getKitchen());
            kostInstance.setLivingRoom(kost.getLivingRoom());
            kostInstance.setParkingMotorcycle(kost.getParkingMotorcycle());
            kostInstance.setParkingCar(kost.getParkingCar());
            kostInstance.setDispenser(kost.getDispenser());


            // From file upload form
            kostInstance.setFrontBuildingPhoto(kostServiceImpl.uploadFile(kost.getFrontBuildingPhoto(), "front_building_photo"));
            kostInstance.setFrontFarbuildingPhoto(kostServiceImpl.uploadFile(kost.getFrontFarbuildingPhoto(), "front_farbuilding_photo"));

            // Add rule to kost
            Rule ruleInstance = new Rule();
            ruleInstance.setRestrictedNight(kost.getRestrictedNight());
            ruleInstance.setIdentityCard(kost.getIdentityCard());
            ruleInstance.setRestrictedGender(kost.getRestrictedGender());
            ruleInstance.setRestrictedGuest(kost.getRestrictedGuest());
            ruleInstance.setMaximumOne(kost.getMaximumOne());
            ruleInstance.setMaximumTwo(kost.getMaximumTwo());
            ruleInstance.setRestrictedCheckin(kost.getRestrictedCheckin());
            ruleInstance.setRestrictedCheckout(kost.getRestrictedCheckout());
            ruleInstance.setIncludeElectricity(kost.getIncludeElectricity());
            ruleInstance.setNoSmoking(kost.getNoSmoking());


            // Assign the new instance kost into current user
            Optional<Profile> user = profileRepo.findById(profileId);

            user.ifPresent(kostInstance::setProfile);

            Rule ruleCreated = ruleRepo.save(ruleInstance);

            kostInstance.setRule(ruleCreated);

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
            @ModelAttribute KostModel kost
    ) throws IOException {

        try {
            Kost kostInstance = kostRepo.checkExistingKostIdAdmin(kostId);
            if (kostInstance == null) {
                return new ResponseEntity<>(res.notFoundError("kost doesn't exist"), HttpStatus.NOT_FOUND);
            }
            kostInstance.setKostName(kost.getKostName());
            kostInstance.setDescription(kost.getDescription());
            kostInstance.setKostTypeMan(kost.getKostTypeMan());
            kostInstance.setKostTypeWoman(kost.getKostTypeWoman());
            kostInstance.setKostTypeMixed(kost.getKostTypeMixed());
            kostInstance.setYearSince(kost.getYearSince());
            kostInstance.setProvince(kost.getProvince());
            kostInstance.setCity(kost.getCity());
            kostInstance.setAddress(kost.getAddress());
            kostInstance.setGmaps(kost.getGmaps());

            // Assign general facility
            kostInstance.setKostTv(kost.getKostTv());
            kostInstance.setElectric(kost.getElectric());
            kostInstance.setLaundry(kost.getLaundry());
            kostInstance.setRefrigerator(kost.getRefrigerator());
            kostInstance.setWater(kost.getWater());
            kostInstance.setWifi(kost.getWifi());
            kostInstance.setDrying_ground(kost.getDrying_ground());
            kostInstance.setKitchen(kost.getKitchen());
            kostInstance.setLivingRoom(kost.getLivingRoom());
            kostInstance.setParkingMotorcycle(kost.getParkingMotorcycle());
            kostInstance.setParkingCar(kost.getParkingCar());
            kostInstance.setDispenser(kost.getDispenser());

            // From file upload form
            kostInstance.setFrontBuildingPhoto(kostServiceImpl.uploadFile(kost.getFrontBuildingPhoto(), "front_building_photo"));
            kostInstance.setFrontFarbuildingPhoto(kostServiceImpl.uploadFile(kost.getFrontFarbuildingPhoto(), "front_farbuilding_photo"));

            // update rule
            Rule ruleInstance = kostInstance.getRule();
            ruleInstance.setRestrictedNight(kost.getRestrictedNight());
            ruleInstance.setIdentityCard(kost.getIdentityCard());
            ruleInstance.setRestrictedGender(kost.getRestrictedGender());
            ruleInstance.setRestrictedGuest(kost.getRestrictedGuest());
            ruleInstance.setMaximumOne(kost.getMaximumOne());
            ruleInstance.setMaximumTwo(kost.getMaximumTwo());
            ruleInstance.setRestrictedCheckin(kost.getRestrictedCheckin());
            ruleInstance.setRestrictedCheckout(kost.getRestrictedCheckout());
            ruleInstance.setIncludeElectricity(kost.getIncludeElectricity());
            ruleInstance.setNoSmoking(kost.getNoSmoking());

            Rule ruleEdited = ruleRepo.save(ruleInstance);

            kostInstance.setRule(ruleEdited);

            Kost kostEdited = kostRepo.save(kostInstance);


            return new ResponseEntity<>(res.resSuccess(kostEdited, "Success edit kost!", 200), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(res.clientError("Failed edit kost!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/room/{kostId}")
    public ResponseEntity<Map<String, Object>> editRoom(
            @PathVariable(value = "kostId") Long kostId,
            @ModelAttribute RoomModel room
            ) throws IOException {
        try{
            Optional<Kost> kost = kostRepo.findById(kostId);
            if (kost == null) {
                return new ResponseEntity<>(res.notFoundError("kost doesn't exist"), HttpStatus.NOT_FOUND);
            }
            if(!kost.isPresent()){
                return new ResponseEntity<>(res.clientError("kost doesn't exist"), HttpStatus.BAD_REQUEST);
            }

            Room roomInstance = new Room();
            roomInstance.setRoomName(room.getRoomName());
            roomInstance.setQuantityRoom(room.getQuantityRoom());
            roomInstance.setAvailableRoom(room.getAvailableRoom());
            roomInstance.setSizeRoom(room.getSizeRoom());
            roomInstance.setEnabled(true);


            // From file upload form
            roomInstance.setInsideRoomPhoto(kostServiceImpl.uploadFile(room.getInsideRoomPhoto(), "inside_room_photo"));
            roomInstance.setOtherRoomPhoto(kostServiceImpl.uploadFile(room.getOtherRoomPhoto(), "other_room_photo"));

            // Create facility for room
            Facility facilityInstance = new Facility();
            facilityInstance.setAc(room.getAc());
            facilityInstance.setPillow(room.getPillow());
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
            facilityInstance.setChair(room.getChair());
            facilityInstance.setRoomTv(room.getRoomTv());

            Facility facilityRoomCreated = facilityRepo.save(facilityInstance);

            // Assign facility that has been created to room
            roomInstance.setFacility(facilityRoomCreated);

            // Attach room into current kost
            roomInstance.setKost(kost.get());

            // Save kost
            Room roomCreated = roomRepo.save(roomInstance);

            if(room.getPriceDaily() !=null){
                Price priceInstanceDaily = new Price();
                priceInstanceDaily.setPrice(room.getPriceDaily());
                priceInstanceDaily.setDurationType(EDurationType.DAILY.name());
                priceInstanceDaily.setRoom(roomCreated);
                priceRepo.save(priceInstanceDaily);
            }
            if(room.getPriceWeekly() != null) {
                Price priceInstanceWeekly = new Price();
                priceInstanceWeekly.setPrice(room.getPriceWeekly());
                priceInstanceWeekly.setDurationType(EDurationType.WEEKLY.name());
                priceInstanceWeekly.setRoom(roomCreated);
                priceRepo.save(priceInstanceWeekly);
            }

            if(room.getPriceMonthly() != null) {
                Price priceInstanceMonthly = new Price();
                priceInstanceMonthly.setPrice(room.getPriceMonthly());
                priceInstanceMonthly.setDurationType(EDurationType.MONTHLY.name());
                priceInstanceMonthly.setRoom(roomCreated);
                priceRepo.save(priceInstanceMonthly);
            }

            if(room.getPriceQuarter() != null) {
                Price priceInstanceQuarter = new Price();
                priceInstanceQuarter.setPrice(room.getPriceQuarter());
                priceInstanceQuarter.setDurationType(EDurationType.QUARTER.name());
                priceInstanceQuarter.setRoom(roomCreated);
                priceRepo.save(priceInstanceQuarter);
            }

            if(room.getPriceSemester() != null) {
                Price priceInstanceSemester = new Price();
                priceInstanceSemester.setPrice(room.getPriceSemester());
                priceInstanceSemester.setDurationType(EDurationType.SEMESTER.name());
                priceInstanceSemester.setRoom(roomCreated);
                priceRepo.save(priceInstanceSemester);
            }


            if(room.getPriceYearly() != null) {
                Price priceInstanceYearly = new Price();
                priceInstanceYearly.setPrice(room.getPriceYearly());
                priceInstanceYearly.setDurationType(EDurationType.YEARLY.name());
                priceInstanceYearly.setRoom(roomCreated);
                priceRepo.save(priceInstanceYearly);
            }

            return new ResponseEntity<>(res.resSuccess(roomCreated, "success", 201), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(res.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<Map<String, Object>> createRoom(
            @PathVariable(value = "id") Long roomId,
            @ModelAttribute RoomModel room
    ) throws IOException {
        try{
            Room roomInstance = roomRepo.checkExistingRoomId(roomId);
            if (roomInstance == null) {
                return new ResponseEntity<>(res.notFoundError("kost doesn't exist"), HttpStatus.NOT_FOUND);
            }

            roomInstance.setRoomName(room.getRoomName());
            roomInstance.setQuantityRoom(room.getQuantityRoom());
            roomInstance.setAvailableRoom(room.getAvailableRoom());
            roomInstance.setSizeRoom(room.getSizeRoom());


            // From file upload form
            roomInstance.setInsideRoomPhoto(kostServiceImpl.uploadFile(room.getInsideRoomPhoto(), "inside_room_photo"));
            roomInstance.setOtherRoomPhoto(kostServiceImpl.uploadFile(room.getOtherRoomPhoto(), "other_room_photo"));

            // Create facility for room
            Facility facilityInstance = roomInstance.getFacility();
            facilityInstance.setAc(room.getAc());
            facilityInstance.setPillow(room.getPillow());
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
            facilityInstance.setChair(room.getChair());
            facilityInstance.setRoomTv(room.getRoomTv());

            Facility facilityRoomCreated = facilityRepo.save(facilityInstance);

            // Assign facility that has been created to room
            roomInstance.setFacility(facilityRoomCreated);


            // Save kost
            Room roomCreated = roomRepo.save(roomInstance);

            priceRepo.deletePriceByRoomId(roomId);

            if(room.getPriceDaily() !=null){
                Price priceInstanceDaily = new Price();
                priceInstanceDaily.setPrice(room.getPriceDaily());
                priceInstanceDaily.setDurationType(EDurationType.DAILY.name());
                priceInstanceDaily.setRoom(roomCreated);
                priceRepo.save(priceInstanceDaily);
            }
            if(room.getPriceWeekly() != null) {
                Price priceInstanceWeekly = new Price();
                priceInstanceWeekly.setPrice(room.getPriceWeekly());
                priceInstanceWeekly.setDurationType(EDurationType.WEEKLY.name());
                priceInstanceWeekly.setRoom(roomCreated);
                priceRepo.save(priceInstanceWeekly);
            }

            if(room.getPriceMonthly() != null) {
                Price priceInstanceMonthly = new Price();
                priceInstanceMonthly.setPrice(room.getPriceMonthly());
                priceInstanceMonthly.setDurationType(EDurationType.MONTHLY.name());
                priceInstanceMonthly.setRoom(roomCreated);
                priceRepo.save(priceInstanceMonthly);
            }

            if(room.getPriceQuarter() != null) {
                Price priceInstanceQuarter = new Price();
                priceInstanceQuarter.setPrice(room.getPriceQuarter());
                priceInstanceQuarter.setDurationType(EDurationType.QUARTER.name());
                priceInstanceQuarter.setRoom(roomCreated);
                priceRepo.save(priceInstanceQuarter);
            }

            if(room.getPriceSemester() != null) {
                Price priceInstanceSemester = new Price();
                priceInstanceSemester.setPrice(room.getPriceSemester());
                priceInstanceSemester.setDurationType(EDurationType.SEMESTER.name());
                priceInstanceSemester.setRoom(roomCreated);
                priceRepo.save(priceInstanceSemester);
            }


            if(room.getPriceYearly() != null) {
                Price priceInstanceYearly = new Price();
                priceInstanceYearly.setPrice(room.getPriceYearly());
                priceInstanceYearly.setDurationType(EDurationType.YEARLY.name());
                priceInstanceYearly.setRoom(roomCreated);
                priceRepo.save(priceInstanceYearly);
            }

            return new ResponseEntity<>(res.resSuccess(roomCreated, "success", 200), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(res.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/kost/list/{profileId}")
    public ResponseEntity<Map> getListKostTennant(
            @PathVariable Long profileId,
            @RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size) {
        Pageable show_data = PageRequest.of(page, size);
        Page<Kost> list = null;
        list = kostRepo.getListDataTennant(profileId, show_data);
        return new ResponseEntity<Map>(res.resSuccess(list, "Success get list kost", 200), HttpStatus.OK);
    }

    @GetMapping(value = {"/kost/get/{id}"})
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.getByIdTennant(id), HttpStatus.OK);
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<Map<String, Object>> deleteRoomById(@PathVariable Long id) {
        try{
            Optional<Room> room = roomRepo.findById(id);

            if (room.isPresent()) {
                room.get().setDeletedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
                Room roomDeleted = roomRepo.save(room.get());
                room.get().getFacility().setDeletedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
                Facility facilityDeleted = facilityRepo.save(room.get().getFacility());
                return new ResponseEntity<>(res.resSuccess(roomDeleted, "success", 200), HttpStatus.OK);
            }

            return new ResponseEntity<>(res.notFoundError("room doesn't exist"), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(res.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
