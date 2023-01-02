package pl.project.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.admin.order.model.AdminOrderLog;

public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long> {
}
