package Comprar.Carrito.security.router;

import Comprar.Carrito.security.model.UserDTO;
import Comprar.Carrito.security.usecases.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class UserRouter {
    @Bean
    public RouterFunction<ServerResponse> CreateUSer(CreateUserUseCase createUserUseCase) {
        Function<UserDTO, Mono<ServerResponse>> executor = userDTO -> createUserUseCase.apply(userDTO)
                .flatMap(result -> ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/public/createUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class).flatMap(executor)
        );

    }
}
