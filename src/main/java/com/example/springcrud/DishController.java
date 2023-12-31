package com.example.springcrud;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes")
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
}
