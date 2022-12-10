package pl.project.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.order.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
