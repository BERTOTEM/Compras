package Comprar.Carrito.security.repository;

import Comprar.Carrito.collections.Invoice;
import Comprar.Carrito.security.collection.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserRepository  extends ReactiveCrudRepository<User,String> {

    Mono<Boolean> existsByEmail(String email);
    Mono<User> findByEmail(String email);

}
