package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.domain.model.dto.VendaDiaria;
import br.com.ccs.delivery.domain.model.specification.filter.VendaDiariaFilter;
import br.com.ccs.delivery.domain.service.VendaQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/estatisticas")
@AllArgsConstructor
public class VendaDiariaController {

    VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    @ResponseStatus(HttpStatus.OK)
    public Collection<VendaDiaria> getVendasDiarias(VendaDiariaFilter vendaDiariaFilter,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.findVendasDiarias(vendaDiariaFilter, timeOffset);
    }
}
