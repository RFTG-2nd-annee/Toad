<<<<<<< HEAD
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

import com.toad.entities.Actor;
import com.toad.repositories.ActorRepository;

@Controller 
@RequestMapping(path = "/toad/actor") 
public class ActorController {
    @Autowired
    private ActorRepository ActorRepository; 

    @PostMapping(path = "/add")
    public @ResponseBody String createActor(
            @RequestParam String first_name,
            @RequestParam String last_name) { 
    
        Actor newActor = new Actor();
        newActor.setFirst_name(first_name);
        newActor.setLast_name(last_name);
    
        ActorRepository.save(newActor);
    
        return "Acteur enregistré avec succès !";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return ActorRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Actor getActorById(@RequestParam Integer id) {
        return ActorRepository.findById(id).orElse(null);
    }

    @PutMapping(path = "/update/{actor_id}")
    public @ResponseBody String updateActor(
            @PathVariable Integer id,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String last_update) {

        Actor actor = ActorRepository.findById(id).orElse(null);
        if (actor == null) {
            return "Acteur non trouvé";
        }

        actor.setFirst_name(first_name);
        actor.setLast_name(last_name);
        actor.setLast_update(last_update);

        ActorRepository.save(actor);
        return "Acteur Mis à jour";
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String deleteFilm(@PathVariable Integer id) {
        ActorRepository.deleteById(id);
        return "Acteur Supprimé";
    }
}
=======
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

import com.toad.entities.Actor;
import com.toad.repositories.ActorRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/toad/actor") // This means URL's start with /film (after Application path)
public class ActorController {
    @Autowired
    private ActorRepository ActorRepository; // Assuming you have a FilmRepository for Film entity

    @PostMapping(path = "/actor")
    public @ResponseBody String createRental(
            @PathVariable Integer actor_id,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam java.sql.Timestamp last_update) {

        Actor newActor = new Actor();
        newActor.setActor_id(actor_id);
        newActor.setFirst_name(first_name);
        newActor.setLast_name(last_name);
        newActor.setLast_update(last_update);

        ActorRepository.save(newActor);

        return "Acteur enregistré avec succès !";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Actor> getAllActors() {
        return ActorRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Actor getActorById(@RequestParam Integer id) {
        Actor actor = ActorRepository.findById(id).orElse(null);
        if (actor != null) {
            Actor filteredActor = new Actor();
            filteredActor.setActor_id(actor.getActor_id());
            filteredActor.setFirst_name(actor.getFirst_name());
            filteredActor.setLast_name(actor.getLast_name());
            filteredActor.setLast_update(actor.getLast_update());
            return filteredActor;
        }
        return null;
    }

    @PutMapping(path = "/update/{actor_id}")
    public @ResponseBody String updateActor(
            @PathVariable Integer id,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam java.sql.Timestamp last_update) {

        Actor actor = ActorRepository.findById(id).orElse(null);
        if (actor == null) {
            return "Acteur non trouvé";
        }

        actor.setFirst_name(first_name);
        actor.setLast_name(last_name);
        actor.setLast_update(last_update);

        ActorRepository.save(actor);
        return "Acteur Mis à jour";
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String deleteFilm(@PathVariable Integer id) {
        ActorRepository.deleteById(id);
        return "Acteur Supprimé";
    }
}
>>>>>>> 397f09e... GetById Method Fixed
