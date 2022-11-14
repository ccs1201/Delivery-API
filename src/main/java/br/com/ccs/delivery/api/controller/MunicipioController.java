package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.MunicipioInput;
import br.com.ccs.delivery.api.model.representation.response.MunicipioResponse;
import br.com.ccs.delivery.api.utils.ResourceLocationUriHelper;
import br.com.ccs.delivery.core.mapper.MapperInterface;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Municipio;
import br.com.ccs.delivery.domain.service.MunicipioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/municipios", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MunicipioController {

    MunicipioService service;
    @MapperQualifier(MapperQualifierType.MUNICIPIO)
    MapperInterface<MunicipioResponse, MunicipioInput, Municipio> mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<MunicipioResponse> getAll() {

        return mapper.toCollection(service.getAll());
    }

    @GetMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "findByID", description = "Localiza um município pelo seu ID")
    @Parameter(description = "ID do Município", example = "24", required = true, name = "municipioId")
    public MunicipioResponse findById(@PathVariable int municipioId) {

        return mapper.toResponseModel(service.findById(municipioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MunicipioResponse save(@RequestBody @Valid MunicipioInput municipioInput) {

        var municipio = service.save(mapper.toEntity(municipioInput));

        ResourceLocationUriHelper.addUriToResponseHeader(municipio.getId());

        return mapper.toResponseModel(municipio);
    }

    @PutMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    public MunicipioResponse update(@PathVariable int municipioId, @RequestBody @Valid MunicipioInput municipioInput) {

        var municipio = service.findById(municipioId);

        mapper.updateEntity(municipioInput, municipio);

        ResourceLocationUriHelper.addUriToResponseHeader(municipio.getId());

        return mapper.toResponseModel(service.update(municipio));
    }

    @DeleteMapping("{municipioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int municipioId) {

        service.delete(municipioId);
    }

}
