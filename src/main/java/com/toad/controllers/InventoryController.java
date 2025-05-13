package com.toad.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.toad.entities.Inventory;
import com.toad.repositories.InventoryDisponibleRepository;
import com.toad.repositories.InventoryRepository;

@Controller
@RequestMapping(path = "/toad/inventory")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryDisponibleRepository inventoryDisponibleRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewInventory(
            @RequestParam Integer film_id,
            @RequestParam Integer store_id,
            @RequestParam java.sql.Timestamp last_update) {

        Inventory inventory = new Inventory();
        Integer inventoryCount = (int) inventoryRepository.count() + 1;
        inventory.setInventoryId(inventoryCount);
        inventory.setFilmId(film_id);
        inventory.setStoreId(store_id);
        inventory.setLastUpdate(last_update);

        inventoryRepository.save(inventory);
        return "Inventaire Sauvegardé : " + inventoryCount;
    }

    // film_id:1
    // store_id:1
    // last_update:2024-01-01 00:00:00.0


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Inventory getInventoryById(@RequestParam Integer id) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        if (inventory != null) {
            Inventory filteredInventory = new Inventory();
            filteredInventory.setInventoryId(inventory.getInventoryId());
            filteredInventory.setFilmId(inventory.getFilmId());
            filteredInventory.setStoreId(inventory.getStoreId());
            filteredInventory.setLastUpdate(inventory.getLastUpdate());
            filteredInventory.setExiste(inventory.getExiste());
            return filteredInventory;
        }
        return null;
    }
    @PutMapping(path = "/update/{id}")
    public @ResponseBody String updateInventory(
            @PathVariable Integer id,
            @RequestParam Integer film_id,
            @RequestParam Integer store_id,
            @RequestParam java.sql.Timestamp last_update,
            @RequestParam Boolean existe
            ) {
        
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        String message;

        if (inventory == null) {
            message = "Inventaire non trouvé";
        } else {
            inventory.setFilmId(film_id);
            inventory.setStoreId(store_id);
            inventory.setLastUpdate(last_update);
            inventory.setExiste(existe);  
            

            inventoryRepository.save(inventory);
            message = "Inventaire Mis à Jour";
        }

        return message;
    }

    // film_id:1
    // store_id:1
    // last_update:2024-01-01 00:00:00.0

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String deleteInventory(@PathVariable Integer id) {
        inventoryRepository.deleteById(id);
        return "Inventaire Supprimé";
    }
    @DeleteMapping("/deleteDVD/{filmId}")
    public @ResponseBody String deleteInventorySimple(@PathVariable Integer filmId) {
        String returnMessage;
        //Rechercher les DVD en stock pour le film reçu en paramètre ()
        List<Integer> inventoryIdList = inventoryDisponibleRepository.findFreeInventoryIdDVD(filmId);
        
        if (inventoryIdList == null) {
            //Revoyer message erreur
            returnMessage = "Erreur : Aucun DVD disponible pour le film ID " + filmId;
        }else{
            //Rechercher le premier dvd de la liste
            Inventory inventory = inventoryRepository.findById(inventoryIdList.get(0)).orElse(null);
            if (inventory != null) {
                //Marquer le DVD comme supprimé
                updateInventory(
                        inventory.getInventoryId(),
                        inventory.getFilmId(),
                        inventory.getStoreId(),
                        inventory.getLastUpdate(),
                        false
                    );
                    returnMessage = "DVD marqué supprimé pour le film ID " + filmId;
            }else{
                //Envoyer message erreur
                returnMessage = "Erreur : Aucun DVD disponible pour le film ID " + filmId;
            }
        }
        return returnMessage;
    }

    
    @GetMapping(path = "/getStockByStore")
    public @ResponseBody List<Map<String, Object>> getGroupedInventory() {
        List<Object[]> groupedResults = inventoryRepository.findGroupedInventory();
        List<Map<String, Object>> jsonResults = new ArrayList<>();
        for (Object[] row : groupedResults) {
            Map<String, Object> result = new HashMap<>();
            result.put("storeId", convertToInteger(row[0]));
            result.put("filmId", convertToInteger(row[1]));
            result.put("title", row[2]);
            result.put("addressId", convertToInteger(row[3]));
            result.put("address", row[4]);
            result.put("district", row[5]);
            result.put("quantity", row[6]);
   
            jsonResults.add(result);
        }
 
        return jsonResults;
    }
   
    @GetMapping("/available/getById")
    public @ResponseBody Integer getAvailableFilmId(@RequestParam Integer id) {
          return inventoryDisponibleRepository.findFreeInventoryId(id);
      }

    /**
     * Méthode utilitaire pour convertir dynamiquement un objet en Integer.
     */
    private Integer convertToInteger(Object value) {
        if (value instanceof Byte) {
            return ((Byte) value).intValue();
        } else if (value instanceof Short) {
            return ((Short) value).intValue();
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Long) {
            return ((Long) value).intValue();
        } else {
            throw new IllegalArgumentException("Type inattendu : " + value.getClass().getName());
        }
    }

    @GetMapping(path = "/stockFilm")
    public @ResponseBody List<Map<String, Object>> getInventoryStockInfo() {
        // Appel de la méthode du repository pour exécuter la requête
        List<Object[]> groupedResults = inventoryRepository.findInventoryStockInfo();
        
        // Création d'une liste pour stocker les résultats au format JSON
        List<Map<String, Object>> jsonResults = new ArrayList<>();
        
        for (Object[] row : groupedResults) {
            Map<String, Object> result = new HashMap<>();
            result.put("title", row[0]); // title du film
            result.put("totalStock", convertToInteger(row[1])); // total_stock
            result.put("totalLoues", convertToInteger(row[2])); // total_loues
            result.put("filmsDisponibles", convertToInteger(row[3])); // films_disponibles
            result.put("filmId", convertToInteger(row[4]));


            jsonResults.add(result);
        }

        return jsonResults;
    }
    
}
