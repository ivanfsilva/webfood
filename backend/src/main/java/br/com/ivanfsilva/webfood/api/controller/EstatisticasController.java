package br.com.ivanfsilva.webfood.api.controller;

import br.com.ivanfsilva.webfood.domain.filter.VendaDiariaFilter;
import br.com.ivanfsilva.webfood.domain.model.dto.VendaDiaria;
import br.com.ivanfsilva.webfood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( path = "/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro ) {
        return vendaQueryService.consultarVendasDiarias(filtro);
    }
}
