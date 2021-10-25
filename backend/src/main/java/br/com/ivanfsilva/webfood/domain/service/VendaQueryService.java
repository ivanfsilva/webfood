package br.com.ivanfsilva.webfood.domain.service;

import br.com.ivanfsilva.webfood.domain.filter.VendaDiariaFilter;
import br.com.ivanfsilva.webfood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
