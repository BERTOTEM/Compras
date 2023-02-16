package Comprar.Carrito.security.useCases;

import com.compras.compras.backend.security.collection.User;
import com.compras.compras.backend.security.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtilsUser {
    public Function<UserDTO, User> mapperToUser(String id){
        return updateUser-> {
            var user = new User();
            user.setId(id);
            user.setUserName(updateUser.getUserName());
            user.setEmail(updateUser.getEmail());
            user.setPassword(updateUser.getPassword());
            user.setRoles(updateUser.getRoles());
            return user;
        };
    }

    public Function<User,UserDTO> mapEntityToUser(){
        return entity -> new UserDTO(
                entity.getId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRoles()

        );
    }
}
