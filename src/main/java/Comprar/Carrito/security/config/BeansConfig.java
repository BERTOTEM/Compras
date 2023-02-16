package Comprar.Carrito.security.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class BeansConfig {

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
