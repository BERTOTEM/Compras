package Comprar.Carrito.usecases;

import Comprar.Carrito.model.InvoiceDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveInvoice {
    Mono<String> apply(@Valid InvoiceDTO invoiceDTO);
}
