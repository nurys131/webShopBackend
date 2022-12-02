package pl.project.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}