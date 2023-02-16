package Comprar.Carrito.security.usecases;

import Comprar.Carrito.security.model.UserDTO;
import Comprar.Carrito.security.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class FindByEmail  implements Function<String,Mono<UserDTO>> {
    private final UserRepository userRepository;
    private final MapperUtilsUser mapperUtilsUser;

    public FindByEmail(UserRepository userRepository, MapperUtilsUser mapperUtilsUser) {
        this.userRepository = userRepository;
        this.mapperUtilsUser = mapperUtilsUser;
    }

    @Override
    public Mono<UserDTO> apply(String email) {
        return userRepository.findByEmail(email).map(mapperUtilsUser.mapEntityToUser());
    }
}
