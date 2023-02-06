package com.synergy.productService.service;

import com.synergy.productService.dto.FilterSortModel;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface KostService {

    String uploadFile(MultipartFile file, String folderName) throws IOException;

    Map getByIdTennant(Long id);

    Map<String, List<Map<String, Object>>> getKostBySearch(String keyword);

    List<Map<String, Object>> getKostByFilterAndSortAndArea(FilterSortModel filterSortModel,
                                                            Pageable pageable);

    Map<String, List<Map<String, Object>>> getKostById(Long id);

    Map getRoomById(Long id);

    Map getPricebyRoomId(Long id);

    Map kostRejectedById(Long id);

    Map kostApprovedById(Long id);

    Map<String, List<Map<String, Object>>> getKostByIdTennantAdmin(Long id);


}
