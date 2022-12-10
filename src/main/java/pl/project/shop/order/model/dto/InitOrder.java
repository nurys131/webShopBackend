package pl.project.shop.order.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.project.shop.order.model.Shipment;

import java.util.List;

@Getter
@Builder
public class InitOrder {
    private List<Shipment> shipment;
}
