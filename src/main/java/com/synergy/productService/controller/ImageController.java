package com.synergy.productService.controller;

import com.synergy.productService.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/seeker/")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("test") String test) throws IOException {
        return imageService.uploadImage(file);
    }
}

