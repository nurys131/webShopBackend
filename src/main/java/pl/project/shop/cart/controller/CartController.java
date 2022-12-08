package pl.project.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.project.shop.cart.controller.dto.CartSummaryDto;
import pl.project.shop.cart.controller.mapper.CartMapper;
import pl.project.shop.cart.model.dto.CartProductDto;
import pl.project.shop.cart.service.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public CartSummaryDto getCart(@PathVariable Long id) {
        return CartMapper.mapToCartSummary(cartService.getCart(id));
    }

    @PutMapping("/{id}")
    public CartSummaryDto addProductToCart(@PathVariable Long id, CartProductDto cartProductDto) {
        return CartMapper.mapToCartSummary(cartService.addProductToCart(id, cartProductDto));
    }
}
