package Comprar.Carrito.security.config;

import Comprar.Carrito.securityHandle.AuthenticationManager;
import Comprar.Carrito.securityHandle.SecurityContextRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfiguration {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.cors().and()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/login").permitAll()
                .pathMatchers("/createUser").permitAll()
                .pathMatchers("/getAllInvoice").permitAll()
                .pathMatchers("/totalPages").hasAnyRole("ADMIN","USER")
                .pathMatchers("/pagination/{pageNumber}").hasAnyRole("ADMIN")
                .pathMatchers("/getName/{name}").hasAnyRole("ADMIN","USER")
                .pathMatchers("/get/{id}").hasAnyRole("ADMIN","USER")
                .pathMatchers("/update/{id}/{quantity}").hasAnyRole("ADMIN","USER")
                .pathMatchers("/create").hasAnyRole("ADMIN","USER")


                .anyExchange().authenticated()
                .and().build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // permitir solicitudes CORS desde cualquier origen
        configuration.addAllowedMethod("*"); // permitir todos los métodos HTTP
        configuration.addAllowedHeader("*"); // permitir todos los encabezados
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;}


}
