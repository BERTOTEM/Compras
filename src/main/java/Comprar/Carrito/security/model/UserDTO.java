package Comprar.Carrito.security.model;

import Comprar.Carrito.security.enums.RoleEnum;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class UserDTO {
    @Id
    private String id;
    @NotBlank(message = "Username es obligatorio")
    private String username;
    @Email(message = "Email no valido")
    private String email;
    @NotBlank(message = "password es obligatorio")
    private String password;
    @NotEmpty(message = "El rol no puede estar vacio")
    private List<RoleEnum > roles;

    public UserDTO() {
    }

    public UserDTO(String id, String username, String email, String password, List<RoleEnum> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }
}
