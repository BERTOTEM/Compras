package Comprar.Carrito.repositories;

import Comprar.Carrito.model.ProductsDTO;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<ProductsDTO[]> findByName(String name);

}
