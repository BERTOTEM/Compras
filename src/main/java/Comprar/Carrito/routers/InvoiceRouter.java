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

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin(origins = "*")
    @RouterOperation(path = "/getAllInvoice",
            produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
            beanClass = InvoiceRouter.class,
            beanMethod = "getAllInvoice",
            operation = @Operation(operationId = "getAllInvoice",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "ok",
                                    content = @Content(schema = @Schema(implementation = InvoiceDTO.class))
                            ), @ApiResponse(responseCode = "404", description = "Error")
                    }))
    public RouterFunction<ServerResponse> getAllInvoice(ListUseCase listUseCase) {
        return route(GET("/getAllInvoice"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), InvoiceDTO.class)));

    }


    @Bean
    @RouterOperation(path = "/getName/{name}",
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
                            ), @ApiResponse(responseCode = "404", description = "Error")
                    }, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "name")}
            )

    )
    public RouterFunction<ServerResponse> getName(FindByNameUseCase findByNameUseCase) {
        return route(GET("/getName/{name}"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByNameUseCase
                                .findByName(request.pathVariable("name")), ProductsDTO[].class))
        );
    }

    @Bean
    @RouterOperation(path = "/get/{id}",
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
                            ), @ApiResponse(responseCode = "404", description = "Error")
                    }, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id")}
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
    @RouterOperation(path = "/update/{id}/{quantity}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PATCH,
            beanClass = InvoiceRouter.class,
            beanMethod = "updateID",
            operation = @Operation(operationId = "updateID",
                    responses = {
                            @ApiResponse(responseCode = "500", description = "Error que se genera por colocar una suma mayor al inventario o comprar un maximo de articulos no permitido "),

                    }, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id"),
                    @Parameter(in = ParameterIn.PATH, name = "quantity")}
            )

    )
    public RouterFunction<ServerResponse> updateID(UpdateProductUseCase updateProductUseCase) {
        return route(PATCH("/update/{id}/{quantity}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(updateProductUseCase.UpdateProduc(request.pathVariable("id")
                                , request.pathVariable("quantity")), ProductsDTO.class))

        );


    }
    @Bean
    @RouterOperation(path = "/pagination/{pageNumber}",
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
                            ), @ApiResponse(responseCode = "404", description = "Error")
                    }, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "pageNumber")}
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
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "OK",
                                    content = @Content(schema = @Schema(implementation = InvoiceDTO.class))
                            ), @ApiResponse(responseCode = "404", description = "Error")
                    },
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = InvoiceDTO.class
                            ))

                    )

            ))
    public RouterFunction<ServerResponse> Create(CreateUseCase createUseCase) {
        Function<InvoiceDTO, Mono<ServerResponse>> executor = invoiceDTO -> createUseCase.apply(invoiceDTO)
                .flatMap(result -> ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(InvoiceDTO.class).flatMap(executor)
        );


    }
    @Bean
    @RouterOperation(path = "/totalPages",
            produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
            beanClass = InvoiceRouter.class,
            beanMethod = "getTotalPages",
            operation = @Operation(operationId = "getTotalPages",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "ok",
                                    content = @Content(schema = @Schema(implementation = Integer.class))
                            ), @ApiResponse(responseCode = "404", description = "ERROR")
                    }))
    public RouterFunction<ServerResponse> getTotalPages(ListUseCase listUseCase) {
        return route(GET("/totalPages"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.getTotalPages(), Integer.class))
        );


    }
    @Bean
    public RouterFunction<ServerResponse>CreateProduct(CreateProductUseCase createProductUseCase) {
        Function<ProductsDTO, Mono<ServerResponse>> executor = productDTO -> createProductUseCase.CrearProdoc(productDTO)
                .flatMap(result -> ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createProduct").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductsDTO.class).flatMap(executor)
        );


    }
    @Bean
    public  RouterFunction<ServerResponse>editProducts(UpdateProductAllUseCase updateProductAllUseCase){
        return route(PUT("/updateProductAll").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductsDTO.class)
                        .flatMap(updateUseCaseDTO -> updateProductAllUseCase.UpdateProductAll(updateUseCaseDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );

    }
    @Bean
    public RouterFunction<ServerResponse> patchState(DeleteProductUseCase deleteProductUseCase) {
        return route(
                PATCH("/changeState/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .body(BodyInserters.fromPublisher(deleteProductUseCase.DeleteProduct(request.pathVariable("id")),ProductsDTO.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse>getAllProducts(ListUseCase listUseCase){
        return route(GET("/getAllProducts"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.getAllProduct(), ProductsDTO.class)));

    }
    @Bean
    public  RouterFunction<ServerResponse>getAccountAll(ListAccountUseCase listAccountUseCase ){
        return route(GET("/getAccount/{name}"),
                request -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listAccountUseCase
                                .getAccount(request.pathVariable("name")), InvoiceDTO.class))


        );


    }
}