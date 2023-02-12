package Comprar.Carrito.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ProductsDTO {

    private String id;

    private String name;

    private Integer inInventory;

    private boolean enabled;

    private Integer min;

    private Integer max;

    private String img;

    private boolean state;

    private Long price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInInventory() {
        return inInventory;
    }

    public void setInInventory(Integer inInventory) {
        this.inInventory = inInventory;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", inInventory=" + inInventory +
                ", enabled=" + enabled +
                ", min=" + min +
                ", max=" + max +
                ", img='" + img + '\'' +
                ", state=" + state +
                ", price=" + price +
                '}';
    }
}
