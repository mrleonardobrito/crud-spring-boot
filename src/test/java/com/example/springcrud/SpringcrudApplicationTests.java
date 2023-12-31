package com.example.springcrud;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = 
SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringcrudApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnADishWhenDataIsSaved() {
		ResponseEntity<String> response = 
		restTemplate.getForEntity("/dish/99", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(99);

		String name = documentContext.read("$.name");
		assertThat(name).isEqualTo("Salada Ravanello");

		String description = documentContext.read("$.description");
		assertThat(description).isEqualTo("Rabanetes, folhas verdes e molho agridoce salpicados com gergelim.");
	}

	@Test
	void shouldNotReturnADishWithAnUnknownId() {
		ResponseEntity<String> response =
		restTemplate.getForEntity("/dish/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	void shouldCreateANewDish() {
		Dish dish = new Dish(null, "Vinagrete", "Tomates temperados com coisinhas que esqueci o nome");

		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/dish", dish, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewDish = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewDish, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		String name = documentContext.read("$.name");
		String description = documentContext.read("$.description");

		assertThat(id).isNotNull();
		assertThat(name).isEqualTo(dish.name());
		assertThat(description).isEqualTo(dish.description());
	}
}
