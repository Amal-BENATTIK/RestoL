package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@CrossOrigin

@RestController

@RequestMapping("/api/photos")
public class PhotoController {

	
	@PostMapping("")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("C:/Users/ULTRAPC/Desktop/localisationrestaurant-react/localisationrestaurant-react/public/uploads/" + file.getOriginalFilename()); // Replace the path with the location where you want to save the file
            Files.write(path, bytes);
            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while uploading file.";
        }
    }
}
