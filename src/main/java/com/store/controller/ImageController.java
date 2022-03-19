package com.store.controller;

import com.store.model.Image;
import com.store.repository.ImageDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/image")
public class ImageController {


    ImageDbRepository imageDbRepository;

    @Autowired
    public ImageController(ImageDbRepository imageDbRepository) {
        this.imageDbRepository = imageDbRepository;
    }

    @PostMapping("/save")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        Image dbImage = new Image();
        dbImage.setName(file.getOriginalFilename());
        dbImage.setContent(file.getBytes());
        imageDbRepository.save(dbImage);
    }


    @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ByteArrayResource downloadImage(@PathVariable Integer imageId) {
        Image image2 = imageDbRepository.getById(imageId);
        byte[] image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        return new ByteArrayResource(image2.getContent());
    }

    @GetMapping(value = "/get-all-count-products")
    public int[] countProducts() {
        List<Image> imageList = imageDbRepository.findAll();
        int[] massiv = new int[imageList.size()];
        int i = 0;
        for (Image image : imageList) {
            massiv[i] = image.getId();
            i++;
        }
        return massiv;
    }

    @GetMapping(value = "/get-last-image")
    public int lastImage() {
        List<Image> imageList = imageDbRepository.findAll();
        return (imageList.size());
    }


}
