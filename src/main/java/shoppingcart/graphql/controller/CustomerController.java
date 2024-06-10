package shoppingcart.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import shoppingcart.graphql.entites.Customer;
import shoppingcart.graphql.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @QueryMapping
    public Customer getCustomerById(@Argument int id) {
        return customerService.getCustomerById(id);
    }

    @QueryMapping
    public List<Customer> fetchAllCustomers() {
        return customerService.fetchAllCustomers();
    }

    @MutationMapping
    public Customer createCustomer(@Argument String name, @Argument String contactDetails) {
        return customerService.createCustomer(name, contactDetails);
    }

    @MutationMapping
    public Customer updateCustomerContactDetails(@Argument int id,@Argument String contactDetails) {
        return customerService.updateCustomerContactDetails(id, contactDetails);
    }

    @MutationMapping
    public boolean deleteCustomer(@Argument int id){
        return customerService.deleteCustomer(id);
    }
}
