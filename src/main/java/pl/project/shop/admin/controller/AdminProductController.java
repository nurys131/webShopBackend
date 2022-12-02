package pl.project.shop.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.shop.admin.model.AdminProduct;
import pl.project.shop.admin.service.AdminProductService;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService productService;

    @GetMapping("/admin/products")
    public Page<AdminProduct> getProducts(Pageable pageable){
        return productService.getProducts(pageable);
    }
}
