package product;

public class Product {
    private int id;
    private String name;
    private int price;
    private String origin;
        private int quantityVailable;

    public Product(int id, String name, int price, String origin, int quantityVailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.quantityVailable = quantityVailable;
    }
    public Product(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getQuantityVailable() {
        return quantityVailable;
    }

    public void setQuantityVailable(int quantityVailable) {
        this.quantityVailable = quantityVailable;
    }
}
