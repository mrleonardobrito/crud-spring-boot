package com.example.springcrud.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springcrud.core.domain.Dish;
import com.example.springcrud.core.services.DishService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DishController {
    @Autowired
    private final DishService dishService;

    @GetMapping("/menu")
    public ResponseEntity<List<Dish>> fetchAll() {
        List<Dish> dishes = dishService.fetchAll();

        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Dish> findById(@PathVariable Long id) {
        Optional<Dish> dishOptional = dishService.findById(id);

        if(!dishOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dishOptional.get());
    }

    @PostMapping("/dish")
    public ResponseEntity<Void> create(@RequestBody Dish newDish, UriComponentsBuilder ucb) {
        Dish savedDish = dishService.createDish(newDish);

        URI locationOfNewDish = ucb.path("/menu/{id}").buildAndExpand(savedDish.getId()).toUri();

        return ResponseEntity.created(locationOfNewDish).build();
    }
}
