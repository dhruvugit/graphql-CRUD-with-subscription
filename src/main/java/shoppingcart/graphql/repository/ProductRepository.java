package shoppingcart.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoppingcart.graphql.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}