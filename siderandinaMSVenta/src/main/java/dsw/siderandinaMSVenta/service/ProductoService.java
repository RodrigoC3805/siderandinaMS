package dsw.siderandinaMSVenta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.siderandinaMSVenta.dto.ProductoRequest;
import dsw.siderandinaMSVenta.dto.ProductoResponse;
import dsw.siderandinaMSVenta.model.Producto;
import dsw.siderandinaMSVenta.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    public List<ProductoResponse> listProductos() {
        return ProductoResponse.fromEntities(productoRepository.findAll());
    }

    public ProductoResponse insertProducto(ProductoRequest productoRequest) {
        Producto producto = new Producto(
            productoRequest.getIdProducto(),
            productoRequest.getSku(),
            productoRequest.getNombre(),
            productoRequest.getPrecioVentaBase(),
            productoRequest.getCostoUnitarioBase(),
            productoRequest.getStock(),
            productoRequest.getStockMin(),
            productoRequest.getUrlImagen(),
            null, // Assuming CategoriaProducto and UnidadesMedida are set later
            null
        );
        producto = productoRepository.save(producto);
        return ProductoResponse.fromEntity(producto);
    }

    public ProductoResponse updateProducto(ProductoRequest productoRequest) {
        Producto producto = new Producto(
            productoRequest.getIdProducto(),
            productoRequest.getSku(),
            productoRequest.getNombre(),
            productoRequest.getPrecioVentaBase(),
            productoRequest.getCostoUnitarioBase(),
            productoRequest.getStock(),
            productoRequest.getStockMin(),
            productoRequest.getUrlImagen(),
            null, // Assuming CategoriaProducto and UnidadesMedida are set later
            null
        );
        producto = productoRepository.save(producto);
        return ProductoResponse.fromEntity(producto);
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public ProductoResponse findProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return ProductoResponse.fromEntity(producto);
    }
    
    // metodo para buscar productos por nombre
    public List<ProductoResponse> buscarPorNombre(String nombre) {
        return ProductoResponse.fromEntities(productoRepository.findByNombreContainingIgnoreCase(nombre));
    }
    public ProductoResponse actualizarStock(ProductoResponse productoResponse) {
        Producto producto = productoRepository.findById(productoResponse.getIdProducto()).get();
        producto.setStock(productoResponse.getStock());
        producto = productoRepository.save(producto);
        return ProductoResponse.fromEntity(producto);
    }

}
