package com.synergy.productService.service;

import com.synergy.productService.entity.Kost;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface KostService {

    String uploadFrontBuildingPhoto(MultipartFile file) throws IOException;
    String uploadFrontRoadPhoto(MultipartFile file) throws IOException;
    String uploadFrontFarbuildingPhoto(MultipartFile file) throws IOException;

    Map<String, Object> getKostByProfileId(Long profileIf);

    public Map getByIdTennant(Long id);
    public Map getByIdSeeker(Long id);
    public Map approveById(Long id);

    List<Map<String, Object>> getKostBySearch(String search, Pageable pageable);



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
                                                Pageable pageable);
}
