package pl.project.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.order.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
