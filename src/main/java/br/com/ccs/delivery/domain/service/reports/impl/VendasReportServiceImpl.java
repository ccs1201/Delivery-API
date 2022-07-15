package br.com.ccs.delivery.domain.service.reports.impl;

import br.com.ccs.delivery.domain.model.specification.filter.VendaDiariaFilter;
import br.com.ccs.delivery.domain.service.VendaQueryService;
import br.com.ccs.delivery.domain.service.reports.VendasReportService;
import br.com.ccs.delivery.domain.service.reports.exception.RelatorioJasperException;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
@AllArgsConstructor
public class VendasReportServiceImpl implements VendasReportService {

    private VendaQueryService vendaQueryService;

    @Override
    public byte[] gerarVendasDiariaReport(VendaDiariaFilter vendaDiariaFilter, String offSet) {

        var inputStream = this.getClass()
                .getResourceAsStream("/reports/vendas-diarias.jasper");


        var parametros = new HashMap<String, Object>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        var vendasDiarias = vendaQueryService.findVendasDiarias(vendaDiariaFilter, offSet);

        var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

        try {
            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RelatorioJasperException("vendas-diaria", e);
        }
    }
}
