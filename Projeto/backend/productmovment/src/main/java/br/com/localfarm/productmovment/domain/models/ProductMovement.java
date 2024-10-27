package br.com.localfarm.productmovment.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Date is mandatory")
    private LocalDateTime date;

    @NotBlank(message = "Type is mandatory")
    private String type; // Entry or Exit

    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    @NotBlank(message = "Purpose is mandatory")
    private String purpose; // Purchase, Sale, or Production
}
