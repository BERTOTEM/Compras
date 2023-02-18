package Comprar.Carrito.security.config;
import Comprar.Carrito.security.usecases.JWTAuthenticationConverter;
import Comprar.Carrito.security.usecases.JWTUtil;
import Comprar.Carrito.security.usecases.JwtAuthenticationFilter;
import Comprar.Carrito.security.usecases.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtil jwtUtil;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.cors().and()
                .authorizeExchange()
                .pathMatchers("/createUser").permitAll()
                .pathMatchers("/login").permitAll()
                .pathMatchers("/webjars/swagger-ui/index.html#/").permitAll()
                .pathMatchers("/changeState/{id}").hasRole("ADMIN")
                .pathMatchers("/updateProductAll").hasRole("ADMIN")
                .pathMatchers("/createProduct").hasRole("ADMIN")
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
                .addFilterAt(jwtAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // permitir solicitudes CORS desde cualquier origen
        configuration.addAllowedMethod("*"); // permitir todos los métodos HTTP
        configuration.addAllowedHeader("*"); // permitir todos los encabezados
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;}


        @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager manager
                = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        manager.setPasswordEncoder(passwordEncoder());
        System.out.println(manager);
        return manager;
    }

   //@Bean
   //public ServerAuthenticationConverter serverAuthenticationConverter() {
   //    return new JWTAuthenticationConverter(jwtUtil);
   //}
   //

    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(authenticationManager(), new JWTAuthenticationConverter(jwtUtil));
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
