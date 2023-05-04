package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuantityRangePetsDTO {

    private RangePetsDTO range;
    int quantity;
}
