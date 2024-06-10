package shoppingcart.graphql.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import shoppingcart.graphql.entites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}



