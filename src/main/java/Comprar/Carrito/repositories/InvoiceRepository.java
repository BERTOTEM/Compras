package Comprar.Carrito.repositories;

import Comprar.Carrito.collections.Invoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface InvoiceRepository extends ReactiveCrudRepository<Invoice,String> {

    Flux<Invoice> findByAccount(String name);
}
