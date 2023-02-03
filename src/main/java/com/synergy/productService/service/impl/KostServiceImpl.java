package com.synergy.productService.service.impl;

import com.cloudinary.Cloudinary;
import com.synergy.productService.dto.FilterSortModel;
import com.synergy.productService.entity.Kost;
import com.synergy.productService.entity.Price;
import com.synergy.productService.entity.Room;
import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.repository.PriceRepo;
import com.synergy.productService.repository.ProfileRepo;
import com.synergy.productService.repository.RoomRepo;
import com.synergy.productService.service.KostService;
import com.synergy.productService.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KostServiceImpl implements KostService {

    private static Logger logger = LoggerFactory.getLogger(KostServiceImpl.class);

    @Autowired
    public Response res;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private KostRepo kostRepo;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private PriceRepo priceRepo;

    public String uploadFrontBuildingPhoto(MultipartFile file) throws IOException {
        Map<String, Object> options = new HashMap<>();
        options.put("folder", "front_building_photo");
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        String publicId = (String) uploadResult.get("public_id");
        String secureUrl = cloudinary.url().secure(true).generate(publicId);
        return secureUrl;
    }

    public String uploadFrontFarbuildingPhoto(MultipartFile file) throws IOException {
        Map<String, Object> options = new HashMap<>();
        options.put("folder", "front_farbuilding_photo");
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        String publicId = (String) uploadResult.get("public_id");
        String secureUrl = cloudinary.url().secure(true).generate(publicId);
        return secureUrl;
    }

    public String uploadFile(MultipartFile file, String folderName) throws IOException {
        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", folderName);
        Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
        String publicId = (String) uploadedFile.get("public_id");
        return cloudinary.url().secure(true).generate(publicId);
    }

    @Override
    public Map getByIdTennant(Long id) {
        try {
            Kost checkingData = kostRepo.checkExistingKostIdAdmin(id);
            if (checkingData == null) {
                return res.notFoundError("Data cannot be found!");
            }
            return res.resSuccess(checkingData, "success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return res.clientError("Error get by id: " + e);
        }
    }

    @Override
    public Map getRoomById(Long id) {
        try {
            Room checkingData = roomRepo.checkExistingRoomId(id);
            if (checkingData == null) {
                return res.notFoundError("Data cannot be found!");
            }
            return res.resSuccess(checkingData, "success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return res.clientError("Error get by id: " + e);
        }
    }

    @Override
    public Map getPricebyRoomId(Long id) {
        try {
            List checkingData = priceRepo.checkExistingRoomId(id);
            if (checkingData == null) {
                return res.notFoundError("Data cannot be found!");
            }
            return res.resSuccess(checkingData, "success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return res.clientError("Error get by id: " + e);
        }
    }





    @Override
    public Map approveById(Long id) {
        try {
            Kost checkingData = kostRepo.checkExistingKostIdAdmin(id);
            if (checkingData == null) {
                return res.notFoundError("Data cannot be found!");
            }
            checkingData.setEnabled(true);
            Kost done = kostRepo.save(checkingData);
            return res.resSuccess(done, "success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return res.clientError("Error get by id: " + e);
        }
    }


    @Override
    public Map rejectById(Long id) {
        try {
            Kost checkingData = kostRepo.checkExistingKostIdAdmin(id);
            if (checkingData == null) {
                return res.notFoundError("Data cannot be found!");
            }
            kostRepo.delete(checkingData);
            return res.resSuccess(checkingData, "success delete", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return res.clientError("Error get by id: " + e);
        }

    }


    @Override
    public List<Map<String, Object>> getKostByFilterAndSortAndArea(FilterSortModel filterSortModel,
                                                                   Pageable pageable) {
        return kostRepo.getKostByFilterSortAndAreaWithPagination(filterSortModel.getAc(), filterSortModel.getPillow(),
                filterSortModel.getFan(), filterSortModel.getFurniture(), filterSortModel.getShower(), filterSortModel.getSitting_closet(), filterSortModel.getSpringbed(),
                filterSortModel.getTable_learning(), filterSortModel.getWater_heater(), filterSortModel.getInside_bathroom(), filterSortModel.getNon_sitting_closet(),
                filterSortModel.getOutside_bathroom(), filterSortModel.getWindows(), filterSortModel.getKost_tv(),
                filterSortModel.getKost_type_man(), filterSortModel.getKost_type_woman(), filterSortModel.getKost_type_mixed(),
                filterSortModel.getDuration_type(), filterSortModel.getPrice_minimum(), filterSortModel.getPrice_maximum(), filterSortModel.getKost_tv(),
                filterSortModel.getElectric(), filterSortModel.getLaundry(), filterSortModel.getRefrigerator(),
                filterSortModel.getWater(), filterSortModel.getWifi(), filterSortModel.getDispenser(), filterSortModel.getDrying_ground(),
                filterSortModel.getKitchen(), filterSortModel.getLiving_room(), filterSortModel.getParking_car(), filterSortModel.getParking_motorcycle(),
                filterSortModel.getProvince(),filterSortModel.getCity(), pageable);
    }

    @Override
    public Map<String, List<Map<String,Object>>> getKostBySearch(String keyword) {
        List<Map<String, Object>> data = kostRepo.getKostBySearchWithPagination(keyword);
        Map<String, List<Map<String,Object>>> res = new HashMap<>();
        List<Map<String,Object>> location = new ArrayList<>();
        List<Map<String,Object>> kost = new ArrayList<>();


        for(Map<String, Object> item : data){
            Map<String, Object> itemLoc = new HashMap<>();
            Map<String, Object> itemKost = new HashMap<>();


            itemLoc.put("province", item.get("province"));
            itemLoc.put("city", item.get("city"));
            itemKost.put("kost_name", item.get("kost_name"));
            itemKost.put("kost_id", item.get("kost_id"));

            location.add(itemLoc);
            kost.add(itemKost);
        }

        res.put("location", location);
        res.put("kost", kost);

        return res;
    }

    @Override
    public Map<String, List<Map<String, Object>>> getKostById(Long id) {
        List<Map<String,Object>> data = kostRepo.getKostById(id);
        Map<String ,List<Map<String,Object>>> resp = new HashMap<>();
        List<Map<String, Object>> room = new ArrayList<>();
        List<Map<String, Object>> kost = new ArrayList<>();

        for(Map<String,Object> response : data){
            Map<String, Object> itemRoom = new HashMap<>();
            Map<String, Object> itemKost = new HashMap<>();

            //Add field room
            itemRoom.put("room_name", response.get("room_name"));
            itemRoom.put("price", response.get("price"));
            itemRoom.put("inside_room_photo", response.get("inside_room_photo"));
            itemRoom.put("duration_type", response.get("duration_type"));
            itemRoom.put("available_room", response.get("available_room"));
            itemRoom.put("size_room", response.get("size_room"));

            //15 facility
            itemRoom.put("ac", response.get("ac"));
            itemRoom.put("chair", response.get("chair"));
            itemRoom.put("fan", response.get("fan"));
            itemRoom.put("furniture", response.get("furniture"));
            itemRoom.put("inside_bathroom", response.get("inside_bathroom"));
            itemRoom.put("non_sitting_closet", response.get("non_sitting_closet"));
            itemRoom.put("outside_bathroom", response.get("outside_bathroom"));
            itemRoom.put("pillow", response.get("pillow"));
            itemRoom.put("room_tv", response.get("room_tv"));
            itemRoom.put("shower", response.get("shower"));
            itemRoom.put("sitting_closet", response.get("sitting_closet"));
            itemRoom.put("springbed", response.get("springbed"));
            itemRoom.put("table_learning", response.get("table_learning"));
            itemRoom.put("water_heater", response.get("water_heater"));
            itemRoom.put("windows", response.get("windows"));

            //Add kost field
            itemKost.put("kost_id", response.get("kost_id"));
            itemKost.put("kost_name", response.get("kost_name"));
            itemKost.put("city", response.get("city"));
            itemKost.put("address", response.get("address"));
            itemKost.put("kost_type_man", response.get("kost_type_man"));
            itemKost.put("kost_type_mixed", response.get("kost_type_mixed"));
            itemKost.put("kost_type_woman", response.get("kost_type_woman"));
            itemKost.put("front_building_photo", response.get("front_building_photo"));

            room.add(itemRoom);
            kost.add(itemKost);
        }

        resp.put("room", room);
        resp.put("kost", kost);
        return resp;

    }
}

