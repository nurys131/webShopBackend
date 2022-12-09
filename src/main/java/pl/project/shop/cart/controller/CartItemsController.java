package pl.project.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.project.shop.cart.service.CartItemsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cartItems")
public class CartItemsController {
    private final CartItemsService cartItemsService;

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemsService.delete(id);
    }

    @GetMapping("/count/{cartId}")
    public Long countItemInCart(@PathVariable Long cartId) {
        return cartItemsService.countItemInCart(cartId);
    }
}
