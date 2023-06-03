package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Ville;
import com.example.demo.entities.Zone;
import com.example.demo.service.VilleService;
import com.example.demo.service.ZoneService;

@CrossOrigin
@RestController
@RequestMapping("/api/zones")
public class ZoneController {

	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private VilleService villeService;
	
	
	@GetMapping("")
	public List<Zone> getAllSpecialtes() {
		return zoneService.findAll();
	}

	/*
	 * @PostMapping("") public void save(@RequestBody Zone s) { zoneService.save(s);
	 * }
	 */
	@PostMapping("/{nom}/{Id_Ville}")
	public void save(@PathVariable String nom,@PathVariable int Id_Ville) {
	    Ville ville = villeService.findById(Id_Ville);
	    if (ville != null) {
	        Zone zone = new Zone();
	        zone.setNom(nom);
	        zone.setVille(ville);
	        zoneService.save(zone);
	    }
	}
	
	
	@PutMapping("/{id}")
    public Zone update(@PathVariable Integer id, @RequestBody Zone s) {
		Zone existingCity = zoneService.findById(id);
		Zone ss=s;
        if (existingCity != null) {
            existingCity.setNom(ss.getNom());
            return zoneService.save(existingCity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
    	zoneService.delete(id);
    }
    
    
    @GetMapping("{id_Ville}")
	public List<Zone> getZonesByVille(@PathVariable int id_Ville) {
		Ville ville = villeService.findById(id_Ville);
        if (ville != null) {
            //existingCity.setName(city.getName());
            return ville.getZones();
        }
		return null;
	}
}
