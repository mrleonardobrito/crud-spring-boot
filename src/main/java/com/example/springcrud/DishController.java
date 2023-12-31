package com.example.springcrud;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishRepository dishRepository;

    private DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
    
    @GetMapping("/{dishID}")
    private ResponseEntity<Dish> findById(@PathVariable Long dishID) {
        Optional<Dish> dishOptional = dishRepository.findById(dishID);
        if(!dishOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dishOptional.get());
    }

    @PostMapping
    private ResponseEntity<Void> createDish(@RequestBody Dish newDish, UriComponentsBuilder ucb) {
        Dish savedDish = dishRepository.save(newDish);

        URI locationOfNewDish = ucb
            .path("/dish/{id}")
            .buildAndExpand(savedDish.id())
            .toUri();
        return ResponseEntity.created(locationOfNewDish).build();
    }
}
