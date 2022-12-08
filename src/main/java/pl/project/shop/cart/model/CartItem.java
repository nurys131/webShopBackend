package pl.project.shop.cart.model;

import lombok.*;
import pl.project.shop.common.model.Product;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private Long cartId;
}
