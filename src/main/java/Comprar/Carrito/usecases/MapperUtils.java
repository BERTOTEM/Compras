package Comprar.Carrito.usecases;

import Comprar.Carrito.collections.Invoice;
import Comprar.Carrito.model.InvoiceDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {
    public Function<InvoiceDTO, Invoice> mapperToInvoice(String id) {
        return updateInvoice -> {
            var invoice = new Invoice();
            invoice.setId(id);
            invoice.setIdtype(updateInvoice.getIdtype());
            invoice.setDate(updateInvoice.getDate());
            invoice.setClientId(updateInvoice.getClientId());
            invoice.setClientName(updateInvoice.getClientName());
            invoice.setAccount(updateInvoice.getAccount());
            invoice.setProducts(updateInvoice.getProducts());

            return invoice;
        };
    }
    public Function<Invoice, InvoiceDTO> mapEntityToInvoice() {
        return entity -> new InvoiceDTO(
                entity.getId(),
                entity.getIdtype(),
                entity.getDate(),
                entity.getClientId(),
                entity.getClientName(),
                entity.getAccount(),
                entity.getProducts()
        );
    }





}
