package Comprar.Carrito.usecases;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.model.ProductsDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;
@Service
@Validated
public class ListUseCase implements Supplier<Flux<InvoiceDTO>> {
    private final InvoiceRepository invoiceRepository;
    private final MapperUtils mapperUtils;
    public ListUseCase(InvoiceRepository invoiceRepository, MapperUtils mapperUtils) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtils = mapperUtils;
    }
    @Override
    public Flux<InvoiceDTO> get() {
        return  invoiceRepository.findAll()
                .map(mapperUtils.mapEntityToInvoice());
    }

    @Autowired
    WebClient webClient;

    public Mono<ProductsDTO[]> Getpage(int pageNumber) {
        return webClient.get()
                .uri("/pagination/{pageNumber}",pageNumber)
                .retrieve()
                .bodyToMono(ProductsDTO[].class);
    }

    public Mono<Integer> getTotalPages() {
        Mono<Integer> result = invoiceRepository.count().map(count -> (int) Math.ceil(count / 5) + 1);
        return result;

    }

}
