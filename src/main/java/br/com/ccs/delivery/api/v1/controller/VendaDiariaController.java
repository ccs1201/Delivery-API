package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.domain.model.dto.VendaDiaria;
import br.com.ccs.delivery.domain.model.specification.filter.VendaDiariaFilter;
import br.com.ccs.delivery.domain.service.VendaQueryService;
import br.com.ccs.delivery.domain.service.reports.VendasReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/estatisticas")
@RequiredArgsConstructor
public class VendaDiariaController {

    private final VendaQueryService vendaQueryService;
    private final VendasReportService vendasReportService;

    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<VendaDiaria> getVendasDiarias(VendaDiariaFilter vendaDiariaFilter,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.findVendasDiarias(vendaDiariaFilter, timeOffset);
    }

    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> getVendasDiariasPdf(VendaDiariaFilter vendaDiariaFilter,
                                                      @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {

        byte[] responsePdf = vendasReportService.gerarVendasDiariaReport(vendaDiariaFilter, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(responsePdf);
    }
}
