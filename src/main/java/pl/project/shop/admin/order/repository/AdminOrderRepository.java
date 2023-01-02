package pl.project.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.project.shop.admin.order.model.AdminOrder;

import java.util.List;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {

    @Query(value = "SELECT o FROM AdminOrder o JOIN FETCH o.orderRows")
    List<AdminOrder> findAdminOrderWithOrderRows();

}
