package Comprar.Carrito.security.repository;


import com.compras.compras.backend.security.collection.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Boolean> existsByEmail(String email);
    Mono<User> findByEmail(String email);

}
