package com.toad.controllers;

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
import com.toad.repositories.FilmRepository;

@Controller
@RequestMapping(path = "/toad/film")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewFilm(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Integer releaseYear,
            @RequestParam Byte languageId,
            @RequestParam Byte originalLanguageId,
            @RequestParam Byte rentalDuration,
            @RequestParam Double rentalRate,
            @RequestParam Integer length,
            @RequestParam Double replacementCost,
            @RequestParam String rating,
<<<<<<< HEAD
            @RequestParam java.sql.Timestamp lastUpdate) {
=======
            @RequestParam java.sql.Timestamp lastUpdate
            // @RequestParam Long idDirector
        ) {
>>>>>>> 1a902252c7427f0c0576ff90143359d61bbcbc90
        
        Film film = new Film();
        film.setTitle(title);
        film.setDescription(description);
        film.setReleaseYear(releaseYear);
        film.setLanguageId(languageId);
        film.setOriginalLanguageId(originalLanguageId);
        film.setRentalDuration(rentalDuration);
        film.setRentalRate(rentalRate);
        film.setLength(length);
        film.setReplacementCost(replacementCost);
        film.setRating(rating);
        film.setLastUpdate(lastUpdate);
<<<<<<< HEAD
=======
        // film.setIdDirector(idDirector);
>>>>>>> 1a902252c7427f0c0576ff90143359d61bbcbc90

        System.out.println(film.getTitle());

        filmRepository.save(film);
        return "Film Sauvegardé";
    }
    
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping(path="/getById")
    public @ResponseBody Film getFilmById(@RequestParam Integer id) {
        Film film = filmRepository.findById(id).orElse(null);
        return film;
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody String updateFilm(
            @PathVariable Integer id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Integer releaseYear,
            @RequestParam Byte languageId,
            @RequestParam Byte originalLanguageId,
            @RequestParam Byte rentalDuration,
            @RequestParam Double rentalRate,
            @RequestParam Integer length,
            @RequestParam Double replacementCost,
            @RequestParam String rating,
<<<<<<< HEAD
            @RequestParam java.sql.Timestamp lastUpdate) {
=======
            @RequestParam java.sql.Timestamp lastUpdate
            // @RequestParam Long idDirector
        ) {
>>>>>>> 1a902252c7427f0c0576ff90143359d61bbcbc90
        
        String status = null;
        if (filmRepository.existsById(id)) {
            Film film = filmRepository.findById(id).orElse(null);
            film.setTitle(title);
            film.setDescription(description);
            film.setReleaseYear(releaseYear);
            film.setLanguageId(languageId);
            film.setOriginalLanguageId(originalLanguageId);
            film.setRentalDuration(rentalDuration);
            film.setRentalRate(rentalRate);
            film.setLength(length);
            film.setReplacementCost(replacementCost);
            film.setRating(rating);
            film.setLastUpdate(lastUpdate);
<<<<<<< HEAD
=======
            // film.setIdDirector(idDirector);
>>>>>>> 1a902252c7427f0c0576ff90143359d61bbcbc90
    
            filmRepository.save(film);
            status = "Film Mis à jour";
        }
        else {
            status = "Film non trouvé";
        }
        return status;
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String deleteFilm(@PathVariable Integer id) {
        String status = null;
        if (filmRepository.existsById(id)) {
          filmRepository.deleteById(id);
          status = "Film supprimé";
        } else {
          status = "Film pas trouvé";
        }
        return status;
    }
}
