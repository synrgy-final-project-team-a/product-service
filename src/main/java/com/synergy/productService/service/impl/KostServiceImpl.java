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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public String uploadFile(MultipartFile file, String folderName) throws IOException {
        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", folderName);
        Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
        String publicId = (String) uploadedFile.get("public_id");
        return cloudinary.url().secure(true).generate(publicId);
    }

    @Override
    public Map<String, Object> getKostByProfileId(Long profileId) {
        try {
            List<Kost> kost = kostRepo.findByProfileId(profileId);
            return res.resSuccess(kost, "success", 200);
        }catch (Exception e){
            return res.internalServerError(e.getMessage());
        }
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
    public List<Map<String, Object>> getKostBySearch(String search, Pageable pageable) {
        return kostRepo.getKostBySearchWithPagination(search, pageable);
    }



    @Override
    public List<Map<String, Object>> getKostByFilter(Boolean ac,
                                                Boolean blanket,
                                                Boolean fan,
                                                Boolean furniture,
                                                Boolean shower,
                                                Boolean sittingCloset,
                                                Boolean springbed,
                                                Boolean tableLearning,
                                                Boolean waterHeater,
                                                Boolean insideBathroom,
                                                Boolean nonsittingCloset,
                                                Boolean outsideBathroom,
                                                Boolean windows,
                                                Boolean roomTv,
                                                Boolean kostTypeMan,
                                                Boolean kostTypeWoman,
                                                Boolean kostTypeMixed,
                                                String durationType,
                                                Double priceMinimum,
                                                Double priceMaximum,
                                                Boolean kostTv,
                                                Boolean electric,
                                                Boolean laundry,
                                                Boolean refrigerator,
                                                Boolean water,
                                                Boolean wifi,
                                                Boolean dispenser,
                                                Boolean dryingGround,
                                                Boolean kitchen,
                                                Boolean livingRoom,
                                                Boolean parking,
                                                Pageable pageable) {




        return kostRepo.getKostByFilterWithPagination(ac, blanket, fan, furniture, shower, sittingCloset, springbed,
                tableLearning, waterHeater, insideBathroom, nonsittingCloset, outsideBathroom, windows, roomTv,
                kostTypeMan, kostTypeWoman, kostTypeMixed, durationType, priceMinimum, priceMaximum, kostTv, electric,
                laundry, refrigerator, water, wifi, dispenser, dryingGround, kitchen, livingRoom, parking,  pageable);
    }
}

