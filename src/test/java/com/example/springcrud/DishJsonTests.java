package com.example.springcrud;

import java.io.IOException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class DishJsonTests {
    @Autowired
    private JacksonTester<Dish> json;

    @Autowired
    private JacksonTester<Dish[]> jsonList;

    private Dish[] dishes;

    @BeforeEach
    void setUp() {
        dishes = Arrays.array(
            new Dish(99L, "Salada Ravanello", "Rabanetes, folhas verdes e molho agridoce salpicados com gergelim."),
            new Dish(100L, "Macarons", "Farinha de amêndoas, manteiga, claras e açúcar."),
            new Dish(101L, "Suco de maracujá", "Suco de maracujá gelado, cremoso, docinho.")
        );
    }

    @Test
    void dishSerializationTest() throws IOException{
        Dish dish = new Dish(1L, "caramelo", "Um belo caramelo assado");

        assertThat(json.write(dish)).isStrictlyEqualToJson("expected_dish.json");
    }

    @Test
    void dishDeserializationTest() throws IOException{
        String expected = """
            {
                "id": 1,
                "name": "caramelo",
                "description": "Um belo caramelo assado"
            }
        """;

        assertThat(json.parse(expected)).isEqualTo(new Dish(1L, "caramelo", "Um belo caramelo assado"));
        assertThat(json.parseObject(expected).id()).isEqualTo(1L);
        assertThat(json.parseObject(expected).name()).isEqualTo("caramelo");
        assertThat(json.parseObject(expected).description()).isEqualTo("Um belo caramelo assado");
    }

    @Test
    void dishListSerializationTest() throws IOException{
        assertThat(jsonList.write(dishes)).isStrictlyEqualToJson("dish_list.json");
    }

    @Test
    void dishListDeserializationTest() throws IOException{
        String expected = """
            [
                {"id": 99, "name": "Salada Ravanello", "description": "Rabanetes, folhas verdes e molho agridoce salpicados com gergelim."},
                {"id": 100, "name": "Macarons", "description": "Farinha de amêndoas, manteiga, claras e açúcar."},
                {"id": 101, "name": "Suco de maracujá", "description": "Suco de maracujá gelado, cremoso, docinho."}
            ]        
        """;

        assertThat(jsonList.parse(expected)).isEqualTo(dishes);
    }
}
