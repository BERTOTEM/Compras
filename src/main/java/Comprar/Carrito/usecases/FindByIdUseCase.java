package Comprar.Carrito.usecases;

import Comprar.Carrito.model.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
@Validated
public class FindByIdUseCase {
    @Autowired
    WebClient webClient;

    public Mono<ProductsDTO> findById(String id) {
        Mono<ProductsDTO> pro=  webClient.get()
                .uri("/get/{id}",id)
                .retrieve()
                .bodyToMono(ProductsDTO.class);
        System.out.println(pro.toString());
        return pro;
    }


}
