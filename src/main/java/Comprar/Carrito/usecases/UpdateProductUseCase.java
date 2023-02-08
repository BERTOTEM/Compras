package Comprar.Carrito.usecases;

import Comprar.Carrito.model.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateProductUseCase {
    @Autowired
    WebClient webClient;

    public Mono<ProductsDTO> UpdateProduc(String id,String quantity) {
        return webClient.patch()
                .uri("/update/{id}/{quantity}",id ,quantity)
                .retrieve()
                .bodyToMono(ProductsDTO.class);
    }
}
