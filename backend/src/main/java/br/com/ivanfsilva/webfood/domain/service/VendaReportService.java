package br.com.ivanfsilva.webfood.domain.service;

import br.com.ivanfsilva.webfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
