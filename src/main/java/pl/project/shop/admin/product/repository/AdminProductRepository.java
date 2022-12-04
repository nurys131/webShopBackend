package pl.project.shop.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.admin.product.model.AdminProduct;

import java.util.List;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
    List<AdminProduct> findAll();
}
