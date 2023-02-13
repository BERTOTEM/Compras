package Comprar.Carrito.routers;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.model.ProductsDTO;
import Comprar.Carrito.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RouterOperation(path="/getAllInvoice",
            produces={MediaType.APPLICATION_JSON_VALUE},method = RequestMethod.GET,
            beanClass =InvoiceRouter.class ,
            beanMethod = "getAllInvoice",
            operation = @Operation(operationId = "getAllInvoice",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "ok",
                                    content = @Content(schema=@Schema(implementation = InvoiceDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")
                    }))
    public RouterFunction<ServerResponse>getAllInvoice(ListUseCase listUseCase){
        return route(GET("/getAllInvoice"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), InvoiceDTO.class)));

    }


    @Bean
    @RouterOperation(path="/getName/{name}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = InvoiceRouter.class,
            beanMethod = "getName",
            operation = @Operation(operationId = "getName",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductsDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")
                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "name")}
            )

    )
    public RouterFunction<ServerResponse> getName(FindByNameUseCase findByNameUseCase){
        return route(GET("/getName/{name}"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByNameUseCase
                                .findByName(request.pathVariable("name")), ProductsDTO[].class))
        );
    }
    @Bean
    @RouterOperation(path="/get/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = InvoiceRouter.class,
            beanMethod = "get",
            operation = @Operation(operationId = "get",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductsDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")
                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "id")}
            )

    )
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
    @RouterOperation(path="/update/{id}/{quantity}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PATCH,
            beanClass = InvoiceRouter.class,
            beanMethod = "updateID",
            operation = @Operation(operationId = "updateID",
                    responses = {
                         @ApiResponse(responseCode = "500",description = "Error que se genera por colocar una suma mayor al inventario o comprar un maximo de articulos no permitido "),

                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "id"),
                    @Parameter(in = ParameterIn.PATH,name = "quantity")}
            )

    )
    public RouterFunction<ServerResponse>updateID(UpdateProductUseCase updateProductUseCase){
        return route(PATCH("/update/{id}/{quantity}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(updateProductUseCase.UpdateProduc(request.pathVariable("id")
                                ,request.pathVariable("quantity")), ProductsDTO.class))

        );


    }


    @Bean
    @RouterOperation(path="/pagination/{pageNumber}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = InvoiceRouter.class,
            beanMethod = "GetpagesAll",
            operation = @Operation(operationId = "GetpagesAll",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = ProductsDTO.class))
                            ),@ApiResponse(responseCode = "404",description = "Error")
                    },parameters = {
                    @Parameter(in = ParameterIn.PATH,name = "pageNumber")}
            )

    )
    public RouterFunction<ServerResponse> GetpagesAll(ListUseCase listUseCase) {
        return route(GET("/pagination/{pageNumber}"),
                request -> ok().body(listUseCase.Getpage(
                        Integer.valueOf(request.pathVariable("pageNumber"))
                ), ProductsDTO.class));
    }
    @Bean
    @RouterOperation(
            path = "/create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            beanClass = InvoiceRouter.class,
            beanMethod = "Create",
            operation = @Operation(operationId = "Create",
                    responses = {
                            @ApiResponse (
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = InvoiceDTO.class))
                            ), @ApiResponse(responseCode = "404",description = "Error")
                    },
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = InvoiceDTO.class
                            ))

                    )

            ))
    public RouterFunction<ServerResponse>Create(CreateUseCase createUseCase){
        Function<InvoiceDTO, Mono<ServerResponse>>executor = invoiceDTO -> createUseCase.apply(invoiceDTO)
                .flatMap(result -> ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(InvoiceDTO.class).flatMap(executor)
        );


}
    @Bean
    @RouterOperation(path="/totalPages",
            produces={MediaType.APPLICATION_JSON_VALUE},method = RequestMethod.GET,
            beanClass = InvoiceRouter.class ,
            beanMethod = "getTotalPages",
            operation = @Operation(operationId = "getTotalPages",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "ok",
                                    content = @Content(schema=@Schema(implementation = Integer.class))
                            ),@ApiResponse(responseCode = "404",description ="ERROR")
                    }))
    public RouterFunction<ServerResponse> getTotalPages(ListUseCase listUseCase) {
        return route(GET("/totalPages"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.getTotalPages(), Integer.class))
        );






    }



}
