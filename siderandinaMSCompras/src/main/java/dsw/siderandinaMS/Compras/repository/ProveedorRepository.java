package dsw.siderandinaMS.Compras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dsw.siderandinaMS.Compras.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    Proveedor findByEmail(String email);
    boolean existsByRuc(String ruc);
    Optional<Proveedor> findByUsuarioIdUsuario(Integer idUsuario);    
}
