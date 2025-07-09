package dsw.siderandinaMS.Compras.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proveedor")
    private Integer idProveedor;

    private String ruc;

    @Column(name = "razon_social")
    private String razonSocial;

    private String direccion;
    private String email;
    private String telefono;

    //@OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    //private Usuario usuario;
}
