package com.example.springcrud.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.springcrud.core.domain.Category;
import com.example.springcrud.core.domain.Dish;
import com.example.springcrud.core.domain.Ingredient;

@DataJpaTest
@DisplayName("Testing Dish Repository")
public class DishRepositoryDataJpaTest {
    @Autowired
    private DishRepository dishRepository;

    /**
     * @throws IOException
     */
    @Test
    @DisplayName("Should return all saved dishes")
    public void shouldReturnAllSavedDishes() throws IOException{
        List<Dish> expectedDishes = Arrays.asList(
          new Dish(
            1L, 
            "Salada Ravanello", 
            "Rabanetes, folhas verdes e molho agridoce salpicados com gergelim. O pão naan dá um toque especial.", 
            new BigDecimal("49.97"), 
            new Category(1L, "Refeições"),
            Arrays.asList(
                new Ingredient(1L, "alface"),
                new Ingredient(2L, "cebola"),
                new Ingredient(3L, "pão naan"),
                new Ingredient(4L, "pepino"),
                new Ingredient(5L, "rabanete"),
                new Ingredient(6L, "tomate")
            ))  
        );

        final List<Dish> actualDishes = dishRepository.findAll();

        assertThat(expectedDishes).isEqualTo(actualDishes);
    }

    @Test
    @DisplayName("Should save a dish")
    public void shouldSaveADish() throws IOException {
        
    }
}



