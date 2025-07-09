package dsw.siderandinaMS.Compras.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pago")
    private Integer idPago;
    @Column(name="monto_pagado")
    private Double montoPagado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_metodo_pago", referencedColumnName = "id_metodo_pago")
    private MetodoPago metodoPago;
}
