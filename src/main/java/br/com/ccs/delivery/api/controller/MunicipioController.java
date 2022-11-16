package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.MunicipioInput;
import br.com.ccs.delivery.api.model.representation.response.EstadoResponse;
import br.com.ccs.delivery.api.model.representation.response.MunicipioResponse;
import br.com.ccs.delivery.api.utils.ResourceLocationUriHelper;
import br.com.ccs.delivery.core.mapper.MunicipioMapper;
import br.com.ccs.delivery.domain.service.MunicipioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/municipios", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService service;
//    @MapperQualifier(MapperQualifierType.MUNICIPIO)
//    MapperInterface<MunicipioResponse, MunicipioInput, Municipio> mapper;
    private final MunicipioMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<MunicipioResponse> getAll() {

        return mapper.toCollectionModel(service.getAll());

    }

    @GetMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "findByID", description = "Localiza um município pelo seu ID")
    @Parameter(description = "ID do Município", example = "24", required = true, name = "municipioId")
    public CompletableFuture<MunicipioResponse> findById(@PathVariable int municipioId) {

        return CompletableFuture.supplyAsync(() -> mapper.toModel(service.findById(municipioId)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MunicipioResponse save(@RequestBody @Valid MunicipioInput municipioInput) {

        var municipio = service.save(mapper.toEntity(municipioInput));

        ResourceLocationUriHelper.addUriToResponseHeader(municipio.getId());

        return mapper.toModel(municipio);
    }

    @PutMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    public MunicipioResponse update(@PathVariable int municipioId, @RequestBody @Valid MunicipioInput municipioInput) {

        var municipio = service.findById(municipioId);

        mapper.updateEntity(municipioInput, municipio);

        ResourceLocationUriHelper.addUriToResponseHeader(municipio.getId());

        return mapper.toModel(service.update(municipio));
    }

    @DeleteMapping("{municipioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int municipioId) {

        service.delete(municipioId);
    }

    /**
     * <p> Adiciona HyperMedia ao {@link MunicipioResponse}</p>
     *
     * @param response {@link MunicipioResponse}
     */
    private void addSelfRef(MunicipioResponse response) {

        response.add(linkTo(
                methodOn(
                        MunicipioController.class)
                        .findById(response.getId()))
                .withSelfRel());

        //Adiciona a HyperMedia ao Estado
        addSelfRef(response.getEstado());

    }

    /**
     * <p>Adiciona HyperMedia ao {@link EstadoResponse}</p>
     *
     * @param response {@link EstadoResponse}
     */
    private void addSelfRef(EstadoResponse response) {

        response.add(
                linkTo(
                        methodOn(EstadoController.class)
                                .findById(response.getId()))
                        .withSelfRel());

    }

}
