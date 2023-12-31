package com.example.springcrud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes")
public class DishController {
    
    @GetMapping("/{dishID}")
    private ResponseEntity<Dish> findById(@PathVariable Long dishID) {
        if(dishID.equals(99L)){
            Dish dish = new Dish(99L, "Salada Ravanello", "Rabanetes, folhas verdes e molho agridoce salpicados com gergelim.");
            return ResponseEntity.ok(dish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
