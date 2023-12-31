package com.example.springcrud;

import org.springframework.data.annotation.Id;

record Dish(@Id Long id, String name, String description) {
    
}
