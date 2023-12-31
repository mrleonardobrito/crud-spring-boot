package com.example.springcrud;

import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Long>{}
