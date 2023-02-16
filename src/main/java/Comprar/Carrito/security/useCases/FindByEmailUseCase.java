package Comprar.Carrito.security.useCases;

import com.compras.compras.backend.security.model.UserDTO;
import com.compras.compras.backend.security.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class FindByEmailUseCase {
    private final UserRepository userRepository;
    private final MapperUtilsUser mapperUtils;

    public FindByEmailUseCase(UserRepository userRepository, MapperUtilsUser mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    public Mono<UserDTO> getByEmail(String email) {

        return userRepository.findByEmail(email)
                .map(mapperUtils.mapEntityToUser());
    }

}
