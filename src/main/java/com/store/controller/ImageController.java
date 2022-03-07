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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageDbRepository imageDbRepository;


    @PostMapping("/save")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
  //  public void uploadImage(@RequestParam("image") byte[] image) throws Exception {
    //Long uploadImage() throws Exception {
   //     MultipartFile multipartImage=new MultiparFile();
        Image dbImage = new Image();
        dbImage.setName(file.getName());
        dbImage.setContent(file.getBytes());
   //         Image dbImage = new Image();
   //        dbImage.setName("pictuere.jpeg");
   //         dbImage.setContent(image);
        imageDbRepository.save(dbImage);
       // return imageDbRepository.save(dbImage)
            //    .getId();
    }


/*
    @PostMapping("/save")
    Long uploadImage(@RequestParam int i) throws Exception {
        //Long uploadImage() throws Exception {
        //     MultipartFile multipartImage=new MultiparFile();
       String name="picture.jpeg";
       String originalFilename="picture.jpeg";
       String contentType="image";
        Image dbImage = new Image();
        dbImage.setName(name);
        //dbImage.setContent(bytes);

        return imageDbRepository.save(dbImage)
                .getId();
    }

 */

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ByteArrayResource downloadImage(@PathVariable Integer imageId) {

        Image image2=imageDbRepository.getById(imageId);
        byte[] image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image2.getContent());
    }

}
