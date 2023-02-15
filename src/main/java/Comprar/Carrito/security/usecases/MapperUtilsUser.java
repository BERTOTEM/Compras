package Comprar.Carrito.security.usecases;



import Comprar.Carrito.security.collection.User;
import Comprar.Carrito.security.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class MapperUtilsUser {

    public Function<UserDTO, User> mapperToUser(String id) {
        return updateUser -> {
            var user = new User();
            user.setId(id);
            user.setUsername(updateUser.getUsername());
            user.setEmail(updateUser.getEmail());
            user.setPassword(updateUser.getPassword());
            user.setRoles(updateUser.getRoles());
            return user;
        };
    }
    public Function<User, UserDTO> mapEntityToUser() {
        return entity -> new UserDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRoles()
        );
    }
}
