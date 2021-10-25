package br.com.ivanfsilva.webfood.infrastructure.service;

import br.com.ivanfsilva.webfood.domain.filter.VendaDiariaFilter;
import br.com.ivanfsilva.webfood.domain.model.dto.VendaDiaria;
import br.com.ivanfsilva.webfood.domain.service.VendaQueryService;

import java.util.List;

public class VendasQueryServiceImpl implements VendaQueryService {

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
        return null;
    }

}
