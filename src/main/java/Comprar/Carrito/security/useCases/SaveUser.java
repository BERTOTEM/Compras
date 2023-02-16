package Comprar.Carrito.security.useCases;

import com.compras.compras.backend.security.model.UserDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveUser {
    Mono<String> apply(@Valid UserDTO userDTO);
}
