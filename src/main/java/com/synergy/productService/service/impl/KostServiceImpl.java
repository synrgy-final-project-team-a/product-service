package com.synergy.productService.service.impl;

import com.cloudinary.Cloudinary;
import com.synergy.productService.dto.FilterModel;
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
    public Map getByIdSeeker(Long id) {
        try {
            Kost checkingData = kostRepo.checkExistingKostId(id);
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
    public List<Map<String, Object>> getKostByArea(String province, String city, Pageable pageable) {
        return kostRepo.getKostByAreaWithPagination(province, city, pageable);
    }


    @Override
    public List<Map<String, Object>> getKostByFilter(FilterModel filterModel,
                                                     Pageable pageable) {


        return kostRepo.getKostByFilterWithPagination(filterModel.getAc(), filterModel.getPillow(),
                filterModel.getFan(), filterModel.getFurniture(), filterModel.getShower(), filterModel.getSitting_closet(), filterModel.getSpringbed(),
                filterModel.getTable_learning(), filterModel.getWater_heater(), filterModel.getInside_bathroom(), filterModel.getNon_sitting_closet(),
                filterModel.getOutside_bathroom(), filterModel.getWindows(), filterModel.getKost_tv(),
                filterModel.getKost_type_man(), filterModel.getKost_type_woman(), filterModel.getKost_type_mixed(),
                filterModel.getDuration_type(), filterModel.getPrice_minimum(), filterModel.getPrice_maximum(), filterModel.getKost_tv(),
                filterModel.getElectric(), filterModel.getLaundry(), filterModel.getRefrigerator(),
                filterModel.getWater(), filterModel.getWifi(), filterModel.getDispenser(), filterModel.getDrying_ground(),
                filterModel.getKitchen(), filterModel.getLiving_room(), filterModel.getParking_car(), filterModel.getParking_motorcycle(), pageable);
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

    public List<Map<String, Object>> getKostById(Long id) {
        return kostRepo.getKostById(id);
    }
}

