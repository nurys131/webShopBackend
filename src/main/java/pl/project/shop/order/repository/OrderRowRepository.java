package pl.project.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.order.model.OrderRow;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
