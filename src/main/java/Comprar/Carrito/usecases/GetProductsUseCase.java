package Comprar.Carrito.usecases;

import Comprar.Carrito.model.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetProductsUseCase {
    @Autowired
    WebClient webClient;

    public Mono<Long> GetTotalProducts() {
        return webClient.get()
                .uri("/countProducts")
                .retrieve()
                .bodyToMono(Long.class);
    }
}
