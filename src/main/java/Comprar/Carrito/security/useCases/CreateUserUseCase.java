package Comprar.Carrito.security.useCases;


import com.compras.compras.backend.security.collection.User;
import com.compras.compras.backend.security.model.UserDTO;
import com.compras.compras.backend.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

;

@Service
@Validated
public class CreateUserUseCase implements SaveUser {

    private final UserRepository userRepository;
    private final MapperUtilsUser mapperUtils;

    @Autowired
    PasswordEncoder passwordEncoder;
    public CreateUserUseCase(UserRepository userRepository, MapperUtilsUser mapperUtils) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(UserDTO userDTO) {

        return userRepository.existsByEmail(userDTO.getEmail()).
                flatMap(email ->{
                    if (email){
                        return Mono.error(new Throwable("Ya existe un usuario registrado con este correo"));
                    }else {
                        return userRepository.save(mapperUtils.mapperToUser(null).apply(userDTO))
                                .map(User::getId);
                    }

                }).onErrorReturn("Ya existe un usuario registrado con este correo");
    }
}
