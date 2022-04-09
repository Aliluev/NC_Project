package com.store.controller;

import com.store.model.Image;
import com.store.model.Response;
import com.store.repository.ImageDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/image")
public class ImageController {

    private ImageDbRepository imageDbRepository;

    @Autowired
    public ImageController(ImageDbRepository imageDbRepository) {
        this.imageDbRepository = imageDbRepository;
    }

    @PostMapping("/save")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        Image dbImage = new Image();
        dbImage.setName(file.getOriginalFilename());
        dbImage.setContent(file.getBytes());
        imageDbRepository.save(dbImage);
        return new Response().myResponseOK();
    }

    @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ByteArrayResource downloadImage(@PathVariable Integer imageId) {
        Image image2 = imageDbRepository.getById(imageId);
        return new ByteArrayResource(image2.getContent());
    }

    @GetMapping(value = "/get-all-count-products")
    public int[] countProducts() {
        List<Image> imageList = imageDbRepository.findAll();
        int[] arrayImageId = new int[imageList.size()];
        int i = 0;
        for (Image image : imageList) {
            arrayImageId[i] = image.getId();
            i++;
        }
        return arrayImageId;
    }

    @GetMapping(value = "/get-last-image")
    public int lastImage() {
        List<Image> imageList = imageDbRepository.findAll();
        return (imageList.size());
    }

}
