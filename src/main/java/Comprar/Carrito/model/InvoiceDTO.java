package Comprar.Carrito.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
public class InvoiceDTO {

    @Id
    private String id;
    private String Idtype;
    private LocalDateTime Date;
    private String clientId;
    @NotBlank
    private String ClientName;
    private List products;

    public InvoiceDTO() {
    }

    public InvoiceDTO(String id, String idtype, LocalDateTime date, @NotBlank String clientId, String clientName, List products) {
        this.id = id;
        Idtype = idtype;
        Date = date;
        this.clientId = clientId;
        ClientName = clientName;
        this.products = products;
    }
    public InvoiceDTO(String idtype, LocalDateTime date, String clientId, @NotBlank String clientName, List products) {
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

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
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
