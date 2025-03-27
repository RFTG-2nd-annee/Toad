package com.toad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.toad.entities.Inventory;

public interface InventoryDisponibleRepository extends CrudRepository<Inventory, Integer> {

    
    @Query(value = "SELECT MIN(inventory_id) FROM inventory WHERE film_id = :filmId  AND NOT EXISTS (SELECT rental.inventory_id FROM rental WHERE rental.inventory_id = inventory.inventory_id AND rental.return_date IS NULL);", nativeQuery = true)
    Integer findFreeInventoryId(@Param("filmId") Integer filmId);
    

}