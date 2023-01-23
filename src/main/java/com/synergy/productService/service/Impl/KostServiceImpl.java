package com.synergy.productService.service.Impl;

import com.synergy.productService.entity.Kost;
import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.service.KostService;
import com.synergy.productService.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KostServiceImpl implements KostService {

    private static Logger logger = LoggerFactory.getLogger(KostServiceImpl.class);
    @Autowired
    private KostRepo kostRepository;
    @Autowired
    public Response templateResponse;

    @Override
    public Map getById(Long id) {
        try {
            Kost checkingData = kostRepository.getById(id);
            if (checkingData == null) {
                return templateResponse.notFoundError("Data cannot be found!");
            }
            return templateResponse.resSuccess(checkingData,"success", 200);

        } catch (Exception e) {
            logger.error("Error get by id, {} " + e);
            return templateResponse.clientError("Error get by id: " + e);
        }
    }
}

