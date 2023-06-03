package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Serie;
import com.example.demo.service.SerieService;


@CrossOrigin
@RestController
@RequestMapping("/api/series")
public class SerieController {
	
	@Autowired
	private SerieService serieService;
	
	
	@GetMapping("")
	public List<Serie> getAllSpecialtes() {
		return serieService.findAll();
	}

	@PostMapping("")
	public void save(@RequestBody Serie s) {
		serieService.save(s);
	}
	
	
	@PutMapping("/{id}")
    public Serie update(@PathVariable Integer id, @RequestBody Serie s) {
		Serie existingCity = serieService.findById(id);
		Serie ss = s;
        if (existingCity != null) {
            existingCity.setNom(ss.getNom());
            return serieService.save(existingCity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
    	serieService.delete(id);
    }
	

}
