package dsw.siderandinaMS.Compras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.siderandinaMS.Compras.dto.CategoriaProductoResponse;
import dsw.siderandinaMS.Compras.repository.CategoriaProductoRepository;

@Service
public class CategoriaProductoService {
    @Autowired
    CategoriaProductoRepository categoriaProductoRepository;

    public List<CategoriaProductoResponse> getCategoriaProducto() {
        return CategoriaProductoResponse.fromEntities(categoriaProductoRepository.findAll());
    }
}
