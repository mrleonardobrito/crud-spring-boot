package com.example.springcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcrud.core.domain.Dish;

public interface DishRepository extends JpaRepository<Dish, Long>{
}
