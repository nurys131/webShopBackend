package pl.project.shop.admin.order.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class AdminOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime placeDate;
    @Enumerated(EnumType.STRING)
    private AdminOrderStatus orderStatus;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<AdminOrderRow> orderRows;
    private BigDecimal grossValue;
    private String firstname;
    private String lastname;
    private String street;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    @OneToOne
    private AdminPayment payment;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<AdminOrderLog> orderLogs;
}
