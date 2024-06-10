package shoppingcart.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import shoppingcart.graphql.entites.Order;
import shoppingcart.graphql.service.OrderService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @QueryMapping
    public List<Order> fetchOrderDetailsByCustomerId(@Argument int customerId) {
        return orderService.fetchOrderDetailsByCustomerId(customerId);
    }

    @QueryMapping
    public Order fetchOrderDetailsByOrderId(@Argument int orderId) {
        return orderService.fetchOrderDetailsByOrderId(orderId);
    }

    @MutationMapping
    public Order createOrder(@Argument int customerId,@Argument String orderNumber, @Argument String status, @Argument List<Integer> productIds) {
        return orderService.createOrder(customerId, orderNumber, status, productIds);
    }
}
