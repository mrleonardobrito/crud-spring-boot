package com.example.springcrud;

import static org.assertj.core.api.Assertions.assertThat;

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
		restTemplate.getForEntity("/dishes/99", String.class);

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
		restTemplate.getForEntity("/dishes/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}
}
