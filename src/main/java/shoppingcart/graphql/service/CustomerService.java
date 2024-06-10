package shoppingcart.graphql.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.graphql.entites.Customer;
import shoppingcart.graphql.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(String name, String contactDetails) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setContactDetails(contactDetails);
        return customerRepository.save(customer);
    }

    public Customer updateCustomerContactDetails(int id, String contactDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setContactDetails(contactDetails);
        return customerRepository.save(customer);
    }

    public boolean deleteCustomer(int id) {
        customerRepository.deleteById(id);
        return true;
    }
}
