package pl.project.shop.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.admin.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
