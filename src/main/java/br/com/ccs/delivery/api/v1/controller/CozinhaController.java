package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.mapper.CozinhaMapper;
import br.com.ccs.delivery.api.v1.model.representation.input.CozinhaInput;
import br.com.ccs.delivery.api.v1.model.representation.response.CozinhaResponse;
import br.com.ccs.delivery.domain.model.entity.Cozinha;
import br.com.ccs.delivery.domain.model.wrapper.CozinhaXmlResponse;
import br.com.ccs.delivery.domain.service.CozinhaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
@Slf4j
@RestController
@RequestMapping(value = "/api/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class CozinhaController {

    //Podemos usar o anotações do lombok @Slf4j
   //private static final Logger LOGGER = LoggerFactory.getLogger(CozinhaController.class);

    private final CozinhaService service;
//    @MapperQualifier(MapperQualifierType.COZINHA)
//    MapperInterface<CozinhaResponse, CozinhaInput, Cozinha> cozinhaMapper;

    private final CozinhaMapper mapper;

    private final PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<CozinhaResponse> findAll(@PageableDefault(size = 10) Pageable pageable) {

        log.info("Listando cozinhas com pagina de tamanho {} ...", pageable.getPageSize());

        var cozinhasPage = service.findAll(pageable);

        return mapper.toPagedModel(cozinhasPage);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CozinhaXmlResponse findAllXml() {
        return new CozinhaXmlResponse(service.findAll());
    }

    @GetMapping("/nome")
    @ResponseStatus(HttpStatus.OK)
    public Collection<CozinhaResponse> findByNome(@RequestParam String nome) {

        return mapper.toCollection(service.findByNomeContaining(nome));
    }

    @GetMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaResponse findById(@PathVariable Long cozinhaId) {
        return mapper.toModel(service.findById(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaResponse save(@RequestBody @Valid CozinhaInput cozinhaInput) {

        Cozinha cozinha = service.save(mapper.toEntity(cozinhaInput));

        return mapper.toModel(cozinha);
    }

    @PutMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaResponse update(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = service.update(cozinhaId, mapper.toEntity(cozinhaInput));
        return mapper.toModel(cozinha);
    }

    @DeleteMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cozinhaId) {

        service.delete(cozinhaId);
    }

    @GetMapping("/first")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaResponse getFirst() {
        return mapper.toModel(service.getFirst());
    }

}
