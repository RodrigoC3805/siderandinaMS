package dsw.siderandinaMSVenta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dsw.siderandinaMSVenta.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByRuc(String ruc);
    Optional<Cliente> findByUsuarioEmail(String email);
    Optional<Cliente> findByUsuarioIdUsuario(Integer idUsuario);
}
