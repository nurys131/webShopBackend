package pl.project.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
