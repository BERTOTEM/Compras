package Comprar.Carrito.usecases;

import Comprar.Carrito.model.ProductsDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class FindByNameUseCaseTest {
    @Autowired
    private TestRestTemplate restTemplate;
    //TestRestTemplate de Spring Boot,
    // que es una clase diseñada específicamente
    // para realizar pruebas en clientes REST
    @Test
    public void FindByNameUseCaseTest() {

        String expectedResult = "[{\"id\":\"63e4406ed8eca96db657b831\",\"name\":\"Mando Xbox\",\"inInventory\":200,\"enabled\":true,\"min\":8,\"max\":200,\"img\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Xbox_360_special_edition_transforming_dpad_controller.jpg/1280px-Xbox_360_special_edition_transforming_dpad_controller.jpg\",\"state\":true,\"price\":135000}]"; //lo que se espera

        String url = "http://localhost:8080/getName/Mando Xbox";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);


        assertThat(response.getBody()).isEqualTo(expectedResult);
    }




}