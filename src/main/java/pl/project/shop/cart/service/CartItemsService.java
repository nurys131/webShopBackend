package pl.project.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.project.shop.cart.repository.CartItemRepository;

@Service
@RequiredArgsConstructor
public class CartItemsService {
    private final CartItemRepository cartItemRepository;

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }

    public Long countItemInCart(Long cartId) {
        return cartItemRepository.countByCartId(cartId);
    }
}
