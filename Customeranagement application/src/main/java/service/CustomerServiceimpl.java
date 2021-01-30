package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceimpl implements CustomerService {
    private static Map<Integer,Customer> customers;
    static {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "Thang", "Thang@codegym.vn", "Hanoi"));
        customers.put(2, new Customer(2, "Son", "Son@codegym.vn", "Saigon"));
        customers.put(4, new Customer(4, "Anh", "Anh@codegym.vn", "QuangTri"));
        customers.put(5, new Customer(5, "Tu", "Tu@codegym.vn", "DongThap"));
        customers.put(6, new Customer(6, "Dat", "Dat@codegym.vn", "HaiPhong"));
    }
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customers.put(id, customer);
    }

    @Override
    public void remove(int id) {
        customers.remove(id);
    }
}
