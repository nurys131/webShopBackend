package pl.project.shop.category.model;

import org.springframework.data.domain.Page;
import pl.project.shop.category.model.Category;
import pl.project.shop.product.model.Product;

public record CategoryProductsDto(Category category, Page<Product> products) {
}

