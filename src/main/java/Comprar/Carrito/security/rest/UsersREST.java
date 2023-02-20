package Comprar.Carrito.security.rest;

import Comprar.Carrito.security.collection.User;
import Comprar.Carrito.security.dto.UserDto;
import Comprar.Carrito.security.repository.UserRepository;
import Comprar.Carrito.security.usecases.MapperUtilsUser;
import Comprar.Carrito.securityHandle.PBKDF2Encoder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class UsersREST {
    private PBKDF2Encoder pbkdf2Encoder;
    private MapperUtilsUser mapperUtils;
    private UserRepository userRepository;
    @PostMapping("/createUser")
    public Mono<String> create (@RequestBody UserDto userDTO) {
        System.out.println(userRepository.existsByUsername(userDTO.getUsername()));
        return userRepository.existsByUsername(userDTO.getUsername())
                .flatMap(username -> {
                    if(username){
                        return Mono.error(new Exception("Ya existe este usuario"));
                    }else{
                        System.out.println(userDTO);
                        userDTO.setPassword(pbkdf2Encoder.encode(userDTO.getPassword()));
                        return userRepository.save(mapperUtils
                                        .mapperToUser(null).apply(userDTO))
                                .map(User::getId);
                    }
                }).onErrorReturn("Ya existe el usuario");
    }
}
