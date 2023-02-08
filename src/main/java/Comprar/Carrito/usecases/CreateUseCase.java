package Comprar.Carrito.usecases;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateUseCase implements SaveInvoice{
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
