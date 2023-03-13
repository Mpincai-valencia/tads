package co.edu.umanizales.tads.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ResponseDTO
{
    private int code;
    private Object data;

    private List<ErrorDTO>errors;
}
