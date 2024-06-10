package shoppingcart.graphql.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.graphql.entites.Customer;
import shoppingcart.graphql.entites.Order;
import shoppingcart.graphql.entites.Product;
import shoppingcart.graphql.repository.CustomerRepository;
import shoppingcart.graphql.repository.OrderRepository;
import shoppingcart.graphql.repository.ProductRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> fetchOrderDetailsByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return customer != null ? customer.getOrders() : null;
    }

    public Order fetchOrderDetailsByOrderId(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order createOrder(int customerId, String orderNumber, String status, List<Integer> productIds) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setStatus(status);
        order.setCustomer(customer);

        List<Product> products = productRepository.findAllById(productIds);
        order.setProducts(products);

        products.forEach(product -> product.setOrder(order));

        return orderRepository.save(order);
    }
}
