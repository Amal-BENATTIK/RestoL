
package com.example.demo.controller;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Restaurant;
import com.example.demo.entities.Zone;
import com.example.demo.entities.Serie;
import com.example.demo.entities.Ville;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.SerieService;
import com.example.demo.service.ZoneService;
import com.example.demo.service.VilleService;


@RestController
@CrossOrigin
@RequestMapping("/api/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private SerieService serieService;

	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private VilleService villeService;

	@GetMapping("")
	public List<Restaurant> getAllRestaurants() {
		return restaurantService.findAll();
	}
	
	@GetMapping("/{nom}/{zone}/{serie}")
	public List<Restaurant> getFiltredRestaurants(@PathVariable String nom,@PathVariable int zone,@PathVariable int serie) {
		 
		return restaurantService.findAll();
	}
	
	
	
	
	
	

	@PostMapping("/{nom}/{adresse}/{jouverture}/{houverture}/{hfermeture}/{latitude}/{longtitude}/{rank}/{Id_Serie}/{Id_Zone}")
	public void save(@PathVariable String nom, @PathVariable String adresse, @PathVariable String jouverture, @PathVariable String houverture, @PathVariable String hfermeture,

			 @PathVariable String latitude, @PathVariable String longtitude,

			 @PathVariable Double rank, @PathVariable int Id_Serie,

			@PathVariable int Id_Zone) {
		Serie serie = serieService.findById(Id_Serie);
		Zone zone = zoneService.findById(Id_Zone);
		if (serie != null && zone != null) {
			Restaurant restaurant = new Restaurant();
			restaurant.setAdresse(adresse);
			restaurant.setJour_ouverture(jouverture);
			restaurant.setHeure_fermeture(hfermeture);
			restaurant.setHeure_ouverture(houverture);
			restaurant.setLatitude(latitude);
			restaurant.setLongitude(longtitude);
			restaurant.setNom(nom);
			restaurant.setRank(rank);
			restaurant.setSerie(serie);
			restaurant.setZone(zone);
			restaurantService.save(restaurant);
		}
	}

	/*
	 * @PutMapping("/{id}") public Restaurant update(@PathVariable Integer
	 * id, @RequestBody Restaurant s) { Restaurant existingCity =
	 * restaurantService.findById(id); if (existingCity != null) { //
	 * existingCity.setName(city.getName()); return
	 * restaurantService.save(existingCity); } return null; }
	 */

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		restaurantService.delete(id);
	}

}
