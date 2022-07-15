package br.com.ccs.delivery.domain.service;

import br.com.ccs.delivery.domain.model.dto.VendaDiaria;
import br.com.ccs.delivery.domain.model.specification.filter.VendaDiariaFilter;

import java.util.Collection;

public interface VendaQueryService {

    Collection<VendaDiaria> findVendasDiarias(VendaDiariaFilter vendaDiariaFilter, String timeOffSet);

}
