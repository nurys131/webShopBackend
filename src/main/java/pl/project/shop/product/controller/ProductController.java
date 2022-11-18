package pl.project.shop.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                new Product("Nowy produkt 1", "Kategoria 1", "Opis 1", new BigDecimal("9.99"), "PLN"),
                new Product("Nowy produkt 2", "Kategoria 2", "Opis 4", new BigDecimal("19.99"), "PLN"),
                new Product("Nowy produkt 3", "Kategoria 3", "Opis 3", new BigDecimal("2.99"), "PLN"),
                new Product("Nowy produkt 4", "Kategoria 1", "Opis 2", new BigDecimal("5.90"), "PLN")
        );
    }


}
