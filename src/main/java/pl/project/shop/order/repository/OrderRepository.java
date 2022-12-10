package pl.project.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
