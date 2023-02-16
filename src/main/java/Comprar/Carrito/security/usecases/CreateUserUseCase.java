package Comprar.Carrito.security.usecases;

import Comprar.Carrito.collections.Invoice;
import Comprar.Carrito.security.collection.User;
import Comprar.Carrito.security.model.UserDTO;
import Comprar.Carrito.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
@Service
@Validated
public class CreateUserUseCase implements SaveUser{

    private final UserRepository userRepository;
    private final MapperUtilsUser mapperUtilsUser;

    @Autowired
    PasswordEncoder passwordEncoder;
    public CreateUserUseCase(UserRepository userRepository, MapperUtilsUser mapperUtilsUser) {
        this.userRepository = userRepository;

        this.mapperUtilsUser = mapperUtilsUser;
    }

    @Override
    public Mono<String> apply(UserDTO userDTO) {
       return userRepository.existsByEmail(userDTO.getEmail()).flatMap(
               existe ->{
                   if (existe){
                       return Mono.error(new Exception("Correo existe"));
                   }
                   else {
                       userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                       return userRepository
                               .save(mapperUtilsUser
                                       .mapperToUser(null)
                                       .apply(userDTO)).map(User::getId);
                   }
                   }).onErrorReturn(" Error el Correo ya existe !!");
               }
}
