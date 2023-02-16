package Comprar.Carrito.security.router;

import com.compras.compras.backend.security.model.UserDTO;
import com.compras.compras.backend.security.useCases.AuthLogin;
import com.compras.compras.backend.security.useCases.CreateUserUseCase;
import com.compras.compras.backend.security.useCases.FindByEmailUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class UserRouter {

    @Autowired
    private AuthLogin authHandler;
    @Bean
    public RouterFunction<ServerResponse> createUser(CreateUserUseCase createUserUseCase){
        Function<UserDTO, Mono<ServerResponse>> executor = userDTO -> createUserUseCase.apply(userDTO)
                .flatMap(result -> ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/createUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class).flatMap(executor)
        );

    }

    @Bean
    public RouterFunction<ServerResponse> getByEmail(FindByEmailUseCase getFindByEmailUseCase) {
        return route(
                GET("/getByEmail/{email}"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getFindByEmailUseCase.getByEmail(request.pathVariable("email")),
                                UserDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> authRoute() {
        return RouterFunctions
                .route(POST("/auth/login").and(accept(APPLICATION_JSON)), authHandler::login)
                .andRoute(POST("/auth/signup").and(accept(APPLICATION_JSON)), authHandler::signUp);
    }
}
