package pl.project.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.shop.common.model.Cart;
import pl.project.shop.common.model.CartItem;
import pl.project.shop.common.repository.CartItemRepository;
import pl.project.shop.common.repository.CartRepository;
import pl.project.shop.order.model.Order;
import pl.project.shop.order.model.OrderRow;
import pl.project.shop.order.model.OrderStatus;
import pl.project.shop.order.model.dto.OrderDto;
import pl.project.shop.order.model.dto.OrderSummary;
import pl.project.shop.order.repository.OrderRepository;
import pl.project.shop.order.repository.OrderRowRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Order order = Order.builder()
                .firstname(orderDto.getFirstName())
                .lastname(orderDto.getLastName())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems()))
                .build();
        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId());

        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteByCartId(orderDto.getCartId());

        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .build();
    }

    private BigDecimal calculateGrossValue(List<CartItem> items) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private void saveOrderRows(Cart cart, Long id) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(id)
                        .build()
                )
                .peek(orderRow -> orderRowRepository.save(orderRow))
                .toList();
    }
}
