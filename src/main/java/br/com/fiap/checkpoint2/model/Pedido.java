package br.com.fiap.checkpoint2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Column(name = "cliente_nome", nullable = false)
    private String clienteNome;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "data_pedido", nullable = false, updatable = false)
    private LocalDate dataPedido;

    @PositiveOrZero(message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    @PrePersist
    void preencherDataPedido() {
        if (dataPedido == null) {
            dataPedido = LocalDate.now();
        }
    }
}
