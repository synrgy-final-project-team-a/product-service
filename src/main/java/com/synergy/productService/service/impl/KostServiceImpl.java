package com.synergy.productService.service.impl;

import com.cloudinary.Cloudinary;
import com.synergy.productService.entity.Kost;
import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.repository.ProfileRepo;
import com.synergy.productService.service.KostService;
import com.synergy.productService.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class KostServiceImpl implements KostService {

    private static Logger logger = LoggerFactory.getLogger(KostServiceImpl.class);

    @Autowired
    public Response templateResponse;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private KostRepo kostRepo;
    @Autowired
    private ProfileRepo profileRepo;

    public String uploadFrontBuildingPhoto(MultipartFile file) throws IOException {
        Map<String, Object> options = new HashMap<>();
        options.put("folder", "front_building_photo");
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        String publicId = (String) uploadResult.get("public_id");
        String secureUrl = cloudinary.url().secure(true).generate(publicId);
        return secureUrl;
    }

    public String uploadFrontRoadPhoto(MultipartFile file) throws IOException {
        Map<String, Object> options = new HashMap<>();
        options.put("folder", "front_road_photo");
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
    @Override
    public Map getByIdTennant(Long id) {
        try {
            Kost checkingData = kostRepo.checkExistingKostIdAdmin(id);
            if (checkingData == null) {
                return templateResponse.notFoundError("Data cannot be found!");
            }
            return templateResponse.resSuccess(checkingData,"success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return templateResponse.clientError("Error get by id: " + e);
        }
    }


    @Override
    public Map getByIdSeeker(Long id) {
        try {
            Kost checkingData = kostRepo.checkExistingKostId(id);
            if (checkingData == null) {
                return templateResponse.notFoundError("Data cannot be found!");
            }
            return templateResponse.resSuccess(checkingData,"success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return templateResponse.clientError("Error get by id: " + e);
        }
    }
    @Override
    public Map approveById(Long id) {
        try {
            Kost checkingData = kostRepo.checkExistingKostIdAdmin(id);
            if (checkingData == null) {
                return templateResponse.notFoundError("Data cannot be found!");
            }
            checkingData.setEnabled(true);
            Kost done = kostRepo.save(checkingData);
            return templateResponse.resSuccess(done,"success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return templateResponse.clientError("Error get by id: " + e);
        }
    }

}

