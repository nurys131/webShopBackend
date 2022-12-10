package pl.project.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.shop.common.mail.EmailSimpleService;
import pl.project.shop.common.model.Cart;
import pl.project.shop.common.model.CartItem;
import pl.project.shop.common.repository.CartItemRepository;
import pl.project.shop.common.repository.CartRepository;
import pl.project.shop.order.model.Order;
import pl.project.shop.order.model.OrderRow;
import pl.project.shop.order.model.OrderStatus;
import pl.project.shop.order.model.Payment;
import pl.project.shop.order.model.Shipment;
import pl.project.shop.order.model.dto.OrderDto;
import pl.project.shop.order.model.dto.OrderSummary;
import pl.project.shop.order.repository.OrderRepository;
import pl.project.shop.order.repository.OrderRowRepository;
import pl.project.shop.order.repository.PaymentRepository;
import pl.project.shop.order.repository.ShipmentRepository;

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
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailSimpleService emailSimpleService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
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
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .payment(payment)
                .build();
        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId(), shipment);

        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteByCartId(orderDto.getCartId());
        emailSimpleService.send(order.getEmail(), "Twoje zamówienie zostało przyjęte", createEmailMessage(order));

        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .payment(payment)
                .build();
    }

    private String createEmailMessage(Order order) {
        return "Twoje zamówienie o id: " + order.getId() +
                "\nWartość: " + order.getGrossValue() + " PLN " +
                "\n\n" +
                "\nPłatnośc: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "")+
                "\n\nDziękujemy za zakupy w WebShop.";
    }

    private BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .shipmentId(shipment.getId())
                .orderId(orderId)
                .build());
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(orderId)
                        .build()
                )
                .peek(orderRow -> orderRowRepository.save(orderRow))
                .toList();
    }
}
