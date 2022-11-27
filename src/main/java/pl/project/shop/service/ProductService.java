package pl.project.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.project.shop.product.model.Product;
import pl.project.shop.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
