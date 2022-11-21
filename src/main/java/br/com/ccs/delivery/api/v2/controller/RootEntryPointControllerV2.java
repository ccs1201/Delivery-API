package br.com.ccs.delivery.api.v2.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value =  "/v2",produces = MediaType.APPLICATION_JSON_VALUE)

public class RootEntryPointControllerV2 {

    @GetMapping
    public RootEntryPointModel root() {

        var root = new RootEntryPointModel();

        root.add(linkTo(CidadeControllerV2.class).withRel("municipios"));

        return root;
    }

    private static final class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> { }
}
