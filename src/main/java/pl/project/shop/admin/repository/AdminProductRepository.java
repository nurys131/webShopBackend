package pl.project.shop.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.admin.model.AdminProduct;
import pl.project.shop.product.model.Product;

import java.util.List;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
    List<AdminProduct> findAll();
}
