package com.example.springcrud;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class DishJsonTests {
    @Autowired
    private JacksonTester<Dish> json;

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
}
