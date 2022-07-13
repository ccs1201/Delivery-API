package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.dto.VendaDiaria;
import br.com.ccs.delivery.domain.model.specification.filter.VendaDiariaFilter;
import br.com.ccs.delivery.domain.service.VendaQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/estatisticas")
@AllArgsConstructor
public class VendaDiariaController {

    VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VendaDiaria> getVendasDiarias(VendaDiariaFilter vendaDiariaFilter) {
        return vendaQueryService.findVendasDiarias(vendaDiariaFilter);
    }
}
