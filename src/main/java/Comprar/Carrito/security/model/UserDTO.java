package Comprar.Carrito.security.model;

import com.compras.compras.backend.security.collection.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class UserDTO {

    private String id;

    @NotBlank(message = "El nombre es requerido")
    private String userName;

    @NotBlank(message = "El correo es requerido")
    @Email
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @NotEmpty
    List<Role> roles;

    public UserDTO() {
    }

    public UserDTO(String id, String userName, String email, String password, List<Role> roles) {
        this.id = id;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
