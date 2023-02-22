package Comprar.Carrito.usecases;

import Comprar.Carrito.collections.Invoice;
import Comprar.Carrito.model.InvoiceDTO;
import Comprar.Carrito.repositories.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ListUseCaseTest {

    InvoiceRepository invoiceRepository;
    ListUseCase listUseCase;
    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        invoiceRepository = mock(InvoiceRepository.class);
        listUseCase = new ListUseCase( invoiceRepository,mapperUtils);
    }

    @Test
    void getValidationTest() {

        List productDTO = new ArrayList<>();
        LocalTime fecha = LocalTime.now();
        Invoice invoice= new Invoice();
        InvoiceDTO invoiceDTO= new InvoiceDTO("jk500","CC",fecha,"1017261707","julian","jrtma34@gamil.com",productDTO);
        invoice.setId(invoiceDTO.getId());
        invoice.setIdtype(invoiceDTO.getIdtype());
        invoice.setDate(invoiceDTO.getDate());
        invoice.setClientId(invoiceDTO.getClientId());
        invoice.setClientName(invoiceDTO.getClientName());
        invoice.setProducts(invoiceDTO.getProducts());

        when(invoiceRepository.findAll()).thenReturn(Flux.just(invoice));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(MonoQ -> {
                    assert invoiceDTO.getId().equals("jk500");
                    assert  invoiceDTO.getIdtype().equals("CC");

                    return true;
                })
                .verifyComplete();

        verify(invoiceRepository).findAll();
    }




    }
