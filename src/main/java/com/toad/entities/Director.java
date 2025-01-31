package com.toad.entities;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
 
@Entity // This tells Hibernate to make a table out of this class
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer director_id; // BIGINT
 
    private String nom;
 
    private String prenom;
 
    private Timestamp date_naissance; // Assurez-vous que cet attribut est de type Timestamp
 
    private String nationalite; // Corrigez l'orthographe ici
 
    // Getters and Setters
    public Integer getdirector_id() {
        return director_id;
    }
 
    public void setdirector_id(Integer director_id) {
        this.director_id = director_id;
    }
 
    public String getnom() {
        return nom;
    }
 
    public void setnom(String nom) {
        this.nom = nom;
    }
 
    public String getprenom() {
        return prenom;
    }
 
    public void setprenom(String prenom) {
        this.prenom = prenom;
    }
 
    public Timestamp getdate_naissance() {
        return date_naissance;
    }
 
    public void setdate_naissance(Timestamp date_naissance) {
        this.date_naissance = date_naissance;
    }
 
    public String getnationalite() {
        return nationalite; // Corrigez l'orthographe ici
    }
 
    public void setnationalite(String nationalite) { // Ajoutez cette méthode
        this.nationalite = nationalite; // Corrigez l'orthographe ici
    }
 
}
 