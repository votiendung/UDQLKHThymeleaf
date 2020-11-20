package service;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(int id);

    void update(int id, Customer customer);

    void remove(int id);

    void save(Customer customer);
}
