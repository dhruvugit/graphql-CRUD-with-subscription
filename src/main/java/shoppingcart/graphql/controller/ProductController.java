package shoppingcart.graphql.controller;


import jakarta.annotation.PostConstruct;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import shoppingcart.graphql.entites.Product;
import shoppingcart.graphql.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private FluxSink<Product> productStream;
    private ConnectableFlux<Product> productPublisher;

    @PostConstruct
    public void init(){
        Flux<Product> publisher = Flux.create(emitter -> {
            productStream = emitter;
        });

        productPublisher = publisher.publish(); //Converts Flux to ConnectableFlux (cold to hot stream)
        productPublisher.connect(); //triggers the actual emission of elements from the underlying source
    }

    @QueryMapping
    public Product fetchProductDetailsById(@Argument int productId) {
        return productService.fetchProductDetails(productId);
    }

    @QueryMapping
    public List<Product> fetchAllProductDetails(){
        return productService.fetchAllProductDetails();
    }

    @MutationMapping
    public Product createNewProductEntry(@Argument String productName, @Argument String description, @Argument int price) {
        Product product = productService.createNewProductEntry(productName, description, price);
        productStream.next(product); // Emit the newly created product
        return product;
    }

    @MutationMapping
    public boolean deleteProduct(@Argument int id){
        return productService.deleteProduct(id);
    }

    @MutationMapping
    public boolean deleteMultipleProducts(@Argument List<Integer> ids){
        return productService.deleteMultipleProducts(ids);
    }

    @SubscriptionMapping
    public Publisher<Product> newProductAdded() {
        return productPublisher;
    }
}

