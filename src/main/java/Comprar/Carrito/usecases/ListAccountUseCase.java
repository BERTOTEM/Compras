package Comprar.Carrito.usecases;

import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class ListAccountUseCase {

    private final InvoiceRepository invoiceRepository;
    private final  MapperUtils mapperUtils;

    public ListAccountUseCase(InvoiceRepository invoiceRepository, MapperUtils mapperUtils) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtils = mapperUtils;
    }


    public Flux<InvoiceDTO> getAccount(String name) {
        return invoiceRepository.findByAccount(name).map(mapperUtils.mapEntityToInvoice());
    }


}
