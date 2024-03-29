package br.com.ccs.delivery.api.v2.controller;

import br.com.ccs.delivery.api.utils.ResourceLocationUriHelper;
import br.com.ccs.delivery.api.v2.mapper.CidadeMapperV2;
import br.com.ccs.delivery.api.v2.model.input.CidadeInputV2;
import br.com.ccs.delivery.api.v2.model.response.CidadeResponseV2;
import br.com.ccs.delivery.domain.service.MunicipioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
// usando para versionamento com custom media type
//@RequestMapping(value = "/api/v2/municipios", produces = ApiMediaTypes.V2_APPLICATION_JSON_VALUE)
@RequestMapping(value = "/api/v2/municipios", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class CidadeControllerV2 {

    private final MunicipioService service;
//    @MapperQualifier(MapperQualifierType.MUNICIPIO)
//    MapperInterface<MunicipioResponse, MunicipioInput, Municipio> mapper;
    private final CidadeMapperV2 mapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<CidadeResponseV2> getAll() {

        return mapper.toCollectionModel(service.getAll());

    }

    @GetMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(operationId = "findByID", description = "Localiza um município pelo seu ID")
    @Parameter(description = "ID do Município", example = "24", required = true, name = "municipioId")
    public CompletableFuture<CidadeResponseV2> findById(@PathVariable int municipioId) {

        return CompletableFuture.supplyAsync(() -> mapper.toModel(service.findById(municipioId)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeResponseV2 save(@RequestBody @Valid CidadeInputV2 cidadeInputV2) {

        var municipio = service.save(mapper.toEntity(cidadeInputV2));

        ResourceLocationUriHelper.addUriToResponseHeader(municipio.getId());

        return mapper.toModel(municipio);
    }

    @PutMapping("{municipioId}")
    @ResponseStatus(HttpStatus.OK)
    public CidadeResponseV2 update(@PathVariable int municipioId, @RequestBody @Valid CidadeInputV2 cidadeInputV2) {

        var municipio = service.findById(municipioId);

        mapper.updateEntity(cidadeInputV2, municipio);

        ResourceLocationUriHelper.addUriToResponseHeader(municipio.getId());

        return mapper.toModel(service.update(municipio));
    }

    @DeleteMapping("{municipioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int municipioId) {

        service.delete(municipioId);
    }

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.OK)
    protected void logError(){

        throw new NotImplementedException("Método não implementado....");
    }

}
