package pl.project.shop.category.dto;

import org.springframework.data.domain.Page;
import pl.project.shop.common.model.Category;
import pl.project.shop.common.dto.ProductListDto;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}

