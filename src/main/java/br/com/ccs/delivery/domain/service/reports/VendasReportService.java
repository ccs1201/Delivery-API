package br.com.ccs.delivery.domain.service.reports;

import br.com.ccs.delivery.domain.model.specification.filter.VendaDiariaFilter;

public interface VendasReportService {

    byte[] gerarVendasDiariaReport(VendaDiariaFilter vendaDiariaFilter, String offSet);

}
