package br.com.localfarm.productmovment.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdatedEvent implements Serializable {

    private Long productId;
    private String productName;
    private String productCode;
    private String category;
}