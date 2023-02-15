package Comprar.Carrito.security.usecases;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.security.model.UserDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveUser {

    Mono<String> apply(@Valid UserDTO userDTO);
}
