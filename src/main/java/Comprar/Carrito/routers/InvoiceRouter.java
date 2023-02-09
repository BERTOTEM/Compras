package Comprar.Carrito.routers;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.model.ProductsDTO;
import Comprar.Carrito.usecases.CreateUseCase;
import Comprar.Carrito.usecases.FindByIdUseCase;
import Comprar.Carrito.usecases.FindByNameUseCase;
import Comprar.Carrito.usecases.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class InvoiceRouter {
    @Bean
    public RouterFunction<ServerResponse> getName(FindByNameUseCase findByNameUseCase){
        return route(GET("/getName/{name}"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByNameUseCase
                                .findByName(request.pathVariable("name")), ProductsDTO[].class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> get(FindByIdUseCase findByIdUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByIdUseCase.findById(
                                        request.pathVariable("id")),
                                ProductsDTO.class
                        ))
        );
    }
    @Bean
    public RouterFunction<ServerResponse>updateID(UpdateProductUseCase updateProductUseCase){
        return route(PATCH("/update/{id}/{quantity}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(updateProductUseCase.UpdateProduc(request.pathVariable("id")
                                ,request.pathVariable("quantity")), ProductsDTO.class))

        );


    }
    @Bean
    public  RouterFunction<ServerResponse>editProducts(UpdateProductUseCase updateProductUseCase){
        return route(PUT("/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductsDTO.class)
                        .flatMap(updateUseCaseDTO -> updateProductUseCase.UpdateProductotal(updateUseCaseDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );

    }
    @Bean
    public RouterFunction<ServerResponse> create(CreateUseCase createUseCase){
        Function<InvoiceDTO, Mono<ServerResponse>> executor = billDTO -> createUseCase.apply(billDTO)
                .flatMap(result -> ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/createInvoice").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(InvoiceDTO.class).flatMap(executor)
        );

    }


}
