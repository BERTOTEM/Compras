package Comprar.Carrito.usecases;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.model.ProductsDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import Comprar.Carrito.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Validated
public class CreateUseCase implements SaveInvoice {
    private final InvoiceRepository invoiceRepository;
    private final MapperUtils mapperUtils;


    public CreateUseCase(InvoiceRepository invoiceRepository, MapperUtils mapperUtils) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(InvoiceDTO invoiceDTO) {
        return null;
    }
}
