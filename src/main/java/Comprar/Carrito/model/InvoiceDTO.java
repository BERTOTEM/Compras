package Comprar.Carrito.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

public class InvoiceDTO {

    @Id
    private String id;
    @NotBlank
    private String Idtype;
    @PastOrPresent
    private LocalTime Date;
    @NotBlank
    private String clientId;
    @NotBlank
    private String ClientName;
    private List products;

    public InvoiceDTO() {
    }

    public InvoiceDTO(String id, String idtype, LocalTime date,  String clientId,  String clientName, List products) {
        this.id = id;
        Idtype = idtype;
        Date = date;
        this.clientId = clientId;
        ClientName = clientName;
        this.products = products;
    }
    public InvoiceDTO(String idtype, LocalTime date, String clientId,  String clientName, List products) {
        this.id = id;
        Idtype = idtype;
        Date = date;
        this.clientId = clientId;
        ClientName = clientName;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdtype() {
        return Idtype;
    }

    public void setIdtype(String idtype) {
        Idtype = idtype;
    }

    public LocalTime getDate() {
        return Date;
    }

    public void setDate(LocalTime date) {

        Date = LocalTime.now().plusHours(-5);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public List getProducts() {

        this.products = Optional.ofNullable(products).orElse(new ArrayList<>());
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDTO invoiceDTO = (InvoiceDTO) o;
        return Objects.equals(id, invoiceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "id='" + id + '\'' +
                ", Idtype='" + Idtype + '\'' +
                ", Date=" + Date +
                ", clientId='" + clientId + '\'' +
                ", ClientName='" + ClientName + '\'' +
                ", products=" + products +
                '}';
    }
}
