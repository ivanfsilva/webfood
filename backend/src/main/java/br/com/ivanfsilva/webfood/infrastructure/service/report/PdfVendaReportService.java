package br.com.ivanfsilva.webfood.infrastructure.service.report;

import br.com.ivanfsilva.webfood.domain.filter.VendaDiariaFilter;
import br.com.ivanfsilva.webfood.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportService implements VendaReportService {

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        return null;
    }

}
