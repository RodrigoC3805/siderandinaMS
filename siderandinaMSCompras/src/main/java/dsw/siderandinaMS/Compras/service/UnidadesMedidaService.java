package dsw.siderandinaMS.Compras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsw.siderandinaMS.Compras.model.UnidadesMedida;
import dsw.siderandinaMS.Compras.repository.UnidadesMedidaRepository;

@Service
public class UnidadesMedidaService {
    @Autowired
    UnidadesMedidaRepository unidadesMedidaRepository;

    public List<UnidadesMedida> getUnidadesMedida() {
        return unidadesMedidaRepository.findAll();
    }
}
