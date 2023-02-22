package Comprar.Carrito.usecases;

import Comprar.Carrito.collections.Invoice;
import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import Comprar.Carrito.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CreateUseCaseTest {
    @SpyBean
    private CreateUseCase createUseCase;

    @Autowired
    private MapperUtils mapperUtils;
    @Mock
    InvoiceRepository invoiceRepository;



    @Test
    void CreateUseCaseTestPass() {
        List productDTO = new ArrayList<>();
        LocalTime fecha = LocalTime.now();
        Invoice invoice= new Invoice();
        InvoiceDTO invoiceDTO= new InvoiceDTO("jk500","CC",fecha,"1017261707","julian","jrtam34@gmail.com",productDTO);
        invoice.setId(invoiceDTO.getId());
        invoice.setIdtype(invoiceDTO.getIdtype());
        invoice.setDate(invoiceDTO.getDate());
        invoice.setClientId(invoiceDTO.getClientId());
        invoice.setClientName(invoiceDTO.getClientName());
        invoice.setProducts(invoiceDTO.getProducts());

        Mockito.when(invoiceRepository.save(invoice))
                .thenReturn(Mono.just(mapperUtils
                        .mapperToInvoice(invoiceDTO.getId())
                        .apply(invoiceDTO)));

        StepVerifier.create(createUseCase.apply(invoiceDTO))
                .expectNextMatches(MonoQ -> {

                    assert invoiceDTO.getId().equals("jk500");
                    System.out.println(MonoQ);
                    System.out.println(invoice.getId());
                    return true;
                })
                .verifyComplete();


    }
}