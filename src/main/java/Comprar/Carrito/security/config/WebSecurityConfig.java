package Comprar.Carrito.security.config;
import Comprar.Carrito.security.usecases.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers("/createUser").permitAll()
                .pathMatchers("/webjars/swagger-ui/index.html#/").permitAll()
                .pathMatchers("/getAllInvoice").hasAnyRole("ADMIN","USER")
                .pathMatchers("/getName/{name}").hasAnyRole("ADMIN","USER")
                .pathMatchers("/get/{id}").hasAnyRole("ADMIN","USER")
                .pathMatchers("/update/{id}/{quantity}").hasAnyRole("ADMIN","USER")
                .pathMatchers("/pagination/{pageNumber}").hasAnyRole("ADMIN","USER")
                .pathMatchers("/create").hasAnyRole("ADMIN","USER")
                .pathMatchers("/totalPages").hasAnyRole("ADMIN","USER")


                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .logout().disable()
                .authenticationManager(authenticationManager())
                .build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager manager
                = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        manager.setPasswordEncoder(passwordEncoder());
        System.out.println(manager);
        return manager;
    }

    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
