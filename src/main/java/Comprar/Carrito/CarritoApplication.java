package Comprar.Carrito;

import Comprar.Carrito.model.ProductsDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@SpringBootApplication
public class CarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoApplication.class, args);

		//String url="http://localhost:8080/getAllProducts";
		//WebClient.Builder builder= WebClient.builder();
		//
		//ProductsDTO products = builder
		//		.build()
		//		.get()
		//		.uri(url)
		//		.retrieve()
		//		.bodyToFlux(ProductsDTO.class).blockLast();
		//
		//System.out.println("------------------------");
		//System.out.println(products);
		//System.out.println("------------------------");
	}

}
