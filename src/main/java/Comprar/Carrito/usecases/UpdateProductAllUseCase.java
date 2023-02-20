package Comprar.Carrito.usecases;

import Comprar.Carrito.model.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
@Validated
public class UpdateProductAllUseCase {

    @Autowired
    WebClient webClient;

    public Mono<String> UpdateProductAll(ProductsDTO productsDTO) {
        return webClient.put()
                .uri("/update")
                .body(BodyInserters.fromValue(productsDTO))
                .retrieve()
                .bodyToMono(String.class);
    }
}
