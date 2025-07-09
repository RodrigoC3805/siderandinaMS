package dsw.siderandinaMS.Compras.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="categoria_producto")
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cat_prod")
    private Integer idCatProd;
    private String nombre;
    @Column(name="url_imagen")
    private String urlImagen;
}