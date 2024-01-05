package com.example.springcrud.core.domain;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "dish")
@Getter
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    public Dish() {}

    public Dish setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "description")
    private String description;

    public Dish setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "price")
    private BigDecimal price;

    public Dish setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Dish setCategory(Category category) {
        this.category = category;
        return this;
    }

    @ManyToMany
    @JoinTable(
        name = "dish_ingredients",
        joinColumns = @JoinColumn(name = "dish_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public Dish setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return this.id.equals(dish.id) &&
            this.name.equals(dish.name) &&
            this.description.equals(dish.description) &&
            this.price.equals(dish.price) &&
            this.category.equals(dish.category) &&
            this.ingredients.containsAll(dish.ingredients);
    }

}
