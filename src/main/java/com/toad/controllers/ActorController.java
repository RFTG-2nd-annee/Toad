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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Controller 
@RequestMapping(path = "/toad/actor") 
public class ActorController {
    @Autowired
    private ActorRepository ActorRepository; 

    @PostMapping(path = "/add")
    public @ResponseBody String createActor(
            @RequestParam Integer actor_id,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam Timestamp last_update) { 
    
        Actor newActor = new Actor();
        newActor.setActor_id(actor_id);
        newActor.setFirst_name(first_name);
        newActor.setLast_name(last_name);
        
        // Convertir Timestamp en String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastUpdateStr = sdf.format(last_update);
        newActor.setLast_update(last_update); // Assurez-vous que setLast_update accepte un String
    
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
            @RequestParam Timestamp last_update) {

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