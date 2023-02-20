package Comprar.Carrito.security.dto;

import Comprar.Carrito.security.enums.RolesEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

public class UserDto {

    @Id
    private String id;
    @Getter
    @Setter
    private String username;
    @Getter @Setter
    private String password;



    @Getter @Setter
    private List<RolesEnum> roles;

    public UserDto() {
    }

    public UserDto(String id, String username, String password, List<RolesEnum> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
