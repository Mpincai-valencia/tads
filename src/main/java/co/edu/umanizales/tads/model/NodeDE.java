package co.edu.umanizales.tads.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
public class NodeDE {
    private Pet data;
    private NodeDE next;
    private NodeDE previous;
}
