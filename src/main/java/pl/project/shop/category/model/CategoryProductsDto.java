package pl.project.shop.category.model;

import org.springframework.data.domain.Page;
import pl.project.shop.product.dto.ProductListDto;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}

