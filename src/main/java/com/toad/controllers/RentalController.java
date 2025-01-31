package com.toad.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toad.entities.Film;
import com.toad.entities.Rental;
import com.toad.repositories.FilmRepository;
import com.toad.repositories.RentalRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/toad/rental") // This means URL's start with /film (after Application path)
public class RentalController {
    @Autowired
    private RentalRepository RentalRepository; // Assuming you have a RentalRepository for Rental entity

    @Autowired
    private FilmRepository FilmRepository;

    @PutMapping(path = "/update/{id}") // Map ONLY PUT Requests for updating a film
    public @ResponseBody String updateRental(
            @PathVariable Integer id,
            @RequestParam String rental_date,
            @RequestParam Integer inventory_id,
            @RequestParam Integer customer_id,
            @RequestParam String return_date,
            @RequestParam Integer staff_id,
            @RequestParam String last_update) {

        Rental rental = RentalRepository.findById(id).orElse(null);
        if (rental == null) {
            return "Rental not found";
        }

        rental.setRentalId(id);
        rental.setRentalDate(rental_date);
        rental.setInventoryId(inventory_id);
        rental.setCustomerId(customer_id);
        rental.setReturnDate(return_date);
        rental.setStaffId(staff_id);
        rental.setLastUpdate(last_update);

        RentalRepository.save(rental);
        return "Rental Updated";
    }

    @PostMapping(path = "/add")
    public @ResponseBody String createRental(
            @RequestParam String rental_date,
            @RequestParam Integer inventory_id,
            @RequestParam Integer customer_id,
            @RequestParam String return_date,
            @RequestParam Integer staff_id,
            @RequestParam String last_update) {
    
        Rental newRental = new Rental();
        newRental.setRentalDate(java.time.LocalDateTime.now().toString());
        newRental.setInventoryId(inventory_id);
        newRental.setCustomerId(customer_id);
        newRental.setReturnDate(return_date);
        newRental.setStaffId(staff_id);
        newRental.setLastUpdate(last_update);
    
        RentalRepository.save(newRental);
    
        return "Location créée avec succès !";
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String deleteRental(@PathVariable Integer id) {
       String message;
        if (RentalRepository.existsById(id)) {
            RentalRepository.deleteById(id);
            message = "Location supprimé";
        } else {
            message = "Location avec cet ID n'existe pas";
        } return message;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Rental> getAllUsers() {
        return RentalRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Rental getRentalById(@RequestParam Integer id) {
        Rental rental = RentalRepository.findById(id).orElse(null);
        if (rental != null) {
            Rental filteredRental = new Rental();
            filteredRental.setRentalId(rental.getRentalId());
            filteredRental.setRentalDate(rental.getRentalDate());
            filteredRental.setInventoryId(rental.getInventoryId());
            filteredRental.setCustomerId(rental.getCustomerId());
            filteredRental.setReturnDate(rental.getReturnDate());
            filteredRental.setStaffId(rental.getStaffId());
            filteredRental.setLastUpdate(rental.getLastUpdate());
            return filteredRental;
        }
        return null;
    }

    @GetMapping(path = "/cart")
    public @ResponseBody HashMap<String, Object> getCart() {
        Iterable<Rental> rentals = RentalRepository.findAll();
        Iterable<Film> films = FilmRepository.findAll();
        //splice by 5
        ArrayList<Object> cart = new ArrayList<>();
        for (Rental rental : rentals) {
            HashMap<String, Object> cartItem = new HashMap<>();
            cartItem.put("rental_date", rental.getRentalDate());
            cartItem.put("rental_id", rental.getRentalId());

            Film film = films.iterator().next();
            if (film != null) {
                cartItem.put("film_title", film.getTitle());
            }
            // cartItem.put("customer_name", rental.getCustomerName());
            // cartItem.put("inventory_id", rental.getInventoryId());
            cart.add(cartItem);
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("rentals", cart);
        return response;
    }
}
