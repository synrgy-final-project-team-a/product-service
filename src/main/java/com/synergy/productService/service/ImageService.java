package com.synergy.productService.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.synergy.productService.entity.KostPhoto;
import com.synergy.productService.repository.KostPhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private KostPhotoRepo kostPhotoRepo;

    public Map<String, Object> uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> options = new HashMap<>();
        options.put("folder", "inside_room_photo");
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        String publicId = (String) uploadResult.get("public_id");
        String secureUrl = cloudinary.url().secure(true).generate(publicId);
        kostPhotoRepo.save(secureUrl);
        return result;    }
}

