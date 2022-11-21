package br.com.ccs.delivery.api.v1.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/estatisticas")
public class EstatisticasController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticasModel getEstatisticas() {
        EstatisticasModel estatisticasModel = new EstatisticasModel();
        estatisticasModel.add(linkTo(
                methodOn(VendaDiariaController.class)
                        .getVendasDiarias(null, null)).withSelfRel())
                .add(linkTo(
                        methodOn(VendaDiariaController.class)
                                .getVendasDiariasPdf(null, null)).withRel("pdf"));
        return estatisticasModel;
    }

    private static final class EstatisticasModel extends RepresentationModel<EstatisticasModel> {

    }
}
