package Comprar.Carrito.security.usecases;



import Comprar.Carrito.security.collection.User;
import Comprar.Carrito.security.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class MapperUtilsUser {
    public Function<UserDto, User> mapperToUser(String id) {
        return updateUser -> {
            var userDTO = new User();
            userDTO.setUsername(updateUser.getUsername());
            userDTO.setId(updateUser.getId());
            userDTO.setPassword(updateUser.getPassword());
            userDTO.setRoles(updateUser.getRoles());
            return userDTO;
        };
    }

    public Function<User, UserDto> mapEntityToUser() {
        return entity -> new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoles()
        );
    }
}