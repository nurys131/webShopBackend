package pl.project.shop.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.project.shop.cart.model.Cart;
import pl.project.shop.cart.repository.CartItemRepository;
import pl.project.shop.cart.repository.CartRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartCleanupService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    @Scheduled(cron = "${app.cart.cleanup.expression}")
    public void cleanupOldCarts() {
        List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
        List<Long> ids = carts.stream()
                .map(Cart::getId)
                .toList();
        log.info("Stare koszyki " + carts.size());
        if(!ids.isEmpty()) {
            cartItemRepository.deleteAllByCartIdIn(ids);
            cartRepository.deleteAllByIdIn(ids);
        }
    }
}
