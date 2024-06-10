package shoppingcart.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoppingcart.graphql.entites.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}