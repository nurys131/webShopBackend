package pl.project.shop.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.project.shop.admin.model.AdminProduct;
import pl.project.shop.admin.repository.AdminProductRepository;

@Service
@RequiredArgsConstructor
public class AdminProductService {
    private final AdminProductRepository productRepository;

    public Page<AdminProduct> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}