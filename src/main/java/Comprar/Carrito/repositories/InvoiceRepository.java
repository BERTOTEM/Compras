package Comprar.Carrito.repositories;

import Comprar.Carrito.collections.Invoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface InvoiceRepository extends ReactiveCrudRepository<Invoice,String> {
}
