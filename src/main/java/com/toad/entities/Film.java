package com.toad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;


@Entity // This tells Hibernate to make a table out of this class
public class Film {
    @Id
    @Column(name = "film_id")
    private Integer filmId; // BIGINT

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear; // Year is typically handled as Integer

    @Column(name = "language_id")
    private Byte languageId; // TINYINT

    @Column(name = "original_language_id")
    private Byte originalLanguageId; // TINYINT

    @Column(name = "rental_duration")
    private Byte rentalDuration; // TINYINT

    @Column(name = "rental_rate")
    private Double rentalRate;

    @Column(name = "length")
    private Integer length; // Length typically as Integer

    @Column(name = "replacement_cost")
    private Double replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "last_update")
    private java.sql.Timestamp lastUpdate;

    // Remove or comment out this field if not needed
    // @Column(name = "id_director")
    // private Integer idDirector;

    // Getters and Setters
    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Byte getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Byte languageId) {
        this.languageId = languageId;
    }

    public Byte getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Byte originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Byte getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Byte rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(Double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public java.sql.Timestamp getLastUpdate() {
        // Return defensive copy to maintain immutability
        return lastUpdate != null ? new java.sql.Timestamp(lastUpdate.getTime()) : null;
    }

    public void setLastUpdate(java.sql.Timestamp lastUpdate) {
        if (lastUpdate == null) {
            throw new IllegalArgumentException("lastUpdate cannot be null");
        }
        // Create a defensive copy to prevent external modification
        this.lastUpdate = new java.sql.Timestamp(lastUpdate.getTime());
    }

    // Remove or comment out these methods if not needed
    // public Integer getIdDirector() {
    //     return idDirector;
    // }
    
    // public void setIdDirector(Integer idDirector) {
    //     this.idDirector = idDirector;
    // }

}
