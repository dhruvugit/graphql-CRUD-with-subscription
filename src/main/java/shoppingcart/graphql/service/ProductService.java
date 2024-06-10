package shoppingcart.graphql.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import shoppingcart.graphql.entites.Product;
import shoppingcart.graphql.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    private final Sinks.Many<Product> productSink = Sinks.many().multicast().onBackpressureBuffer();

    public Product fetchProductDetails(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product createNewProductEntry(String productName, String description, int price) {
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);

        product = productRepository.save(product);
        return product;
    }

    public Boolean deleteMultipleProducts(List<Integer> productIds) {
        productRepository.deleteAllById(productIds);
        return true;
    }

    public Boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        return true;
    }

    public List<Product> fetchAllProductDetails() {
        return productRepository.findAll();
    }

//    public Flux<Product> newProductAdded() {
//        return productSink.asFlux();
//    }
}
