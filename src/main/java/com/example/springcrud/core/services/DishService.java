package com.example.springcrud.core.services;
import java.util.List;
import java.util.Optional;

import com.example.springcrud.core.domain.Dish;
import com.example.springcrud.core.interfaces.service.IDishService;
import com.example.springcrud.repository.DishRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService implements IDishService {
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public Dish createDish(Dish dish) {
        return dishRepository.saveAndFlush(dish);
    }

    @Override
    @Transactional
    public List<Dish> fetchAll() {
        return dishRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Dish> findById(Long dishId) {
        return dishRepository.findById(dishId);
    }
    
}
