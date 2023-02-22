package Comprar.Carrito.model;

import org.springframework.data.annotation.Id;




import java.util.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

public class InvoiceDTO {

    @Id
    private String id;
    @NotBlank
    private String Idtype;
    @PastOrPresent
    private Date Date;
    @NotBlank
    private String clientId;
    @NotBlank
    private String ClientName;
    @NotBlank
    private  String account;
    private List products;

    public InvoiceDTO() {
    }

    public InvoiceDTO(String id, String idtype, Date date, String clientId, String clientName, String account, List products) {
        this.id = id;
        Idtype = idtype;
        Date = date;
        this.clientId = clientId;
        ClientName = clientName;
        this.account = account;
        this.products = products;
    }

    public InvoiceDTO(String idtype, Date date, String clientId, String clientName, List products) {
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

    public Date getDate() {
        return Date;
    }

   //public void setDate(LocalTime date) {
   //
   //    Date = LocalTime.now().plusHours(-5);
   //}


    public void setDate(Date date) {
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

        this.products = Optional.ofNullable(products).orElse(new ArrayList<>());
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
