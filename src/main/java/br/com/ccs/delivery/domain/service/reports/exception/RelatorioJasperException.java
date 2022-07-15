package br.com.ccs.delivery.domain.service.reports.exception;

import net.sf.jasperreports.engine.JRException;

public class RelatorioJasperException extends RuntimeException {
    public RelatorioJasperException(String message, JRException cause) {
        super(String.format("Não foi possível gerar o relatório %s", message), cause);
    }

    public RelatorioJasperException(String message) {
        super(String.format("Não foi possível gerar o relatório %s", message));
    }
}
