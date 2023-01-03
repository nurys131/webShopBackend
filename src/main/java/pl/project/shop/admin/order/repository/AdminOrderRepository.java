package pl.project.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.shop.admin.order.model.AdminOrder;
import pl.project.shop.admin.order.model.AdminOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {

    List<AdminOrder> findAllByPlaceDateIsBetweenAndOrderStatus(LocalDateTime from, LocalDateTime to, AdminOrderStatus orderStatus);
}
