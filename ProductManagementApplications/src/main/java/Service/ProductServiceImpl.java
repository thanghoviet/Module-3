package Service;

import product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    public static Map<Integer,Product> products;
    static {
        products = new HashMap<>();
        products.put(1,new Product(1,"iphone 12 pro max",1399,"hongkong",12));
        products.put(2,new Product(2,"samsum S9",850,"korea",8));
        products.put(3,new Product(3,"Nokia 1280",30,"china",1000));
        products.put(4,new Product(4,"blackbery 8",100,"US",3));
        products.put(5,new Product(5,"OPPO Reno 4",300,"VietNam",854));
        products.put(6,new Product(6,"VsMart Aris Pro",400,"VietNam",58));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
