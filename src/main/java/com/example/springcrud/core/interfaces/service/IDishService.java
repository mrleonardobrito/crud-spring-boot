package com.example.springcrud.core.interfaces.service;

import java.util.List;
import java.util.Optional;
import com.example.springcrud.core.domain.Dish;

public interface IDishService {
    Dish createDish(Dish dish);
    List<Dish> fetchAll();
    Optional<Dish> findById(Long dishId);
}
