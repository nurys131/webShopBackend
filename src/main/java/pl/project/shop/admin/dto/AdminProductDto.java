package pl.project.shop.admin.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import pl.project.shop.admin.model.AdminProductCurrency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class AdminProductDto {
    @NotBlank
    @Length(min = 2, max = 50)
    private String name;
    @NotBlank
    @Length(min = 2, max = 50)
    private String category;
    @Length(max = 10000)
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private AdminProductCurrency currency;
}
