package Comprar.Carrito.usecases;

import Comprar.Carrito.model.ProductsDTO;
import Comprar.Carrito.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
@Validated
public class FindByNameUseCase   {
    @Autowired
    WebClient webClient;

    public Mono<ProductsDTO[]> findByName(String name) {
        return webClient.get()
                .uri("/getName/{name}",name)
                .retrieve()
                .bodyToMono(ProductsDTO[].class);
    }




}
