package com.synergy.productService.service;

import com.synergy.productService.dto.KostModel;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface KostService {

    public String uploadFrontBuildingPhoto(MultipartFile file) throws IOException;
    public String uploadFrontRoadPhoto(MultipartFile file) throws IOException;
    public String uploadFrontFarbuildingPhoto(MultipartFile file) throws IOException;

    public Map getByIdTennant(Long id);
    public Map getByIdSeeker(Long id);
    public Map approveById(Long id);

    List<Object> getKostBySearch(String city, String name, Pageable pageable);

    List<Object> getKostByFilter(Boolean ac, Boolean blanket,
                                 Boolean fan, Boolean furniture,
                                 Boolean shower, Boolean sittingCloset,
                                 Boolean springbed, Boolean table, Boolean waterHeater,
                                 Boolean insideBathroom, Boolean nonsittingCloset,
                                 Boolean outsideBathroom, Boolean kostTv,
                                 Boolean kostTypeMan, Boolean kostTypeWoman, Boolean kostTypeMixed,
                                 String durationType,
                                 Double priceMinimum, Double priceMaximum,
                                 Boolean dispenser, Boolean electric,
                                 Boolean laundry, Boolean refrigerator, Boolean water,
                                 Boolean wifi, Boolean dryingGround, Boolean kitchen,
                                 Boolean livingRoom, Boolean parking, Boolean roomTv, Pageable pageable);
}
