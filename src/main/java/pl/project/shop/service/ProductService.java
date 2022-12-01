package pl.project.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.project.shop.product.model.Product;
import pl.project.shop.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}
