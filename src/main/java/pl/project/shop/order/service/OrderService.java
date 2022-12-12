package pl.project.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.shop.common.mail.EmailClientService;
import pl.project.shop.common.model.Cart;
import pl.project.shop.common.repository.CartItemRepository;
import pl.project.shop.common.repository.CartRepository;
import pl.project.shop.order.model.Order;
import pl.project.shop.order.model.Payment;
import pl.project.shop.order.model.Shipment;
import pl.project.shop.order.model.dto.OrderDto;
import pl.project.shop.order.model.dto.OrderSummary;
import pl.project.shop.order.repository.OrderRepository;
import pl.project.shop.order.repository.OrderRowRepository;
import pl.project.shop.order.repository.PaymentRepository;
import pl.project.shop.order.repository.ShipmentRepository;

import static pl.project.shop.order.service.mapper.OrderEmailMessageMapper.createEmailMessage;
import static pl.project.shop.order.service.mapper.OrderMapper.createNewOrder;
import static pl.project.shop.order.service.mapper.OrderMapper.createOrderSummary;
import static pl.project.shop.order.service.mapper.OrderMapper.mapToOrderRow;
import static pl.project.shop.order.service.mapper.OrderMapper.mapToOrderRowWithQuantity;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        sendConfirmEmail(newOrder);
        return createOrderSummary(payment, newOrder);
    }

    private void sendConfirmEmail(Order newOrder) {
        emailClientService.getInstance().send(
                newOrder.getEmail(),
                "Twoje zamówienie zostało przyjęte",
                createEmailMessage(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteByCartId(orderDto.getCartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem)
                )
                .peek(orderRowRepository::save)
                .toList();
    }
}
