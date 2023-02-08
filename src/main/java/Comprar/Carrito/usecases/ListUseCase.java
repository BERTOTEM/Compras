package Comprar.Carrito.usecases;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

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
        return null;
    }
}
