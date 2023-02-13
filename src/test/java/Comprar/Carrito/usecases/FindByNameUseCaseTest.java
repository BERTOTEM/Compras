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

        String expectedResult =
                "[{\"id\":\"63e5bab30667696723fabe55\",\"name\":\"gta\",\"inInventory\":439," +
                        "\"enabled\":true,\"min\":8,\"max\":200,\"img\":\"https://i.blogs.es/9c8d76/gta-v/840_560.jpeg\"," +
                        "\"state\":true,\"price\":60000}]"; //lo que se espera

        String url = "http://localhost:8080/getName/gta";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);


        assertThat(response.getBody()).isEqualTo(expectedResult);
    }




}