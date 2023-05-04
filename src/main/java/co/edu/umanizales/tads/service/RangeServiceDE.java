package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.RangeKidsDTO;
import co.edu.umanizales.tads.controller.dto.RangePetsDTO;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Data
public class RangeServiceDE {
    private List<RangePetsDTO> ranges;

    public RangeServiceDE()
    {

        ranges = new ArrayList<>();
        ranges.add(new RangePetsDTO(1, 3));
        ranges.add(new RangePetsDTO(4, 6));
        ranges.add(new RangePetsDTO(7, 9));
        ranges.add(new RangePetsDTO(10, 12));
        ranges.add(new RangePetsDTO(14, 15));
    }
}
