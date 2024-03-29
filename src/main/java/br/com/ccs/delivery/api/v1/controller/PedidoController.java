package br.com.ccs.delivery.api.v1.controller;

import br.com.ccs.delivery.api.v1.model.representation.input.PedidoInput;
import br.com.ccs.delivery.api.v1.model.representation.response.PedidoResponse;
import br.com.ccs.delivery.api.v1.model.representation.response.filter.PedidoResponseFilter;
import br.com.ccs.delivery.core.exception.FieldValidationException;
import br.com.ccs.delivery.api.v1.mapper.PedidoMapper;
import br.com.ccs.delivery.api.v1.mapper.PedidoResponseFilterMapper;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.model.specification.filter.PedidoFilter;
import br.com.ccs.delivery.domain.service.PedidoService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;
//   @MapperQualifier(MapperQualifierType.PEDIDO)
//   private MapperInterface<PedidoResponse, PedidoInput, Pedido> mapper;

    private final PedidoMapper mapper;
    private final PedidoResponseFilterMapper pedidoResponseFilterMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<PedidoResponse> getAll(Pageable pageable) {

        var pedidosPage = service.findAllEager(pageable);

        return mapper.toPagedModel(pedidosPage); //pagedResourcesAssembler.toModel(response, mapper);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoResponse> filter(PedidoFilter pedidoFilter, @PageableDefault(size = 5) Pageable pageable) {
        return mapper.toPage(service.filter(pedidoFilter, pageable));
    }

    @Transactional
    @GetMapping("/byExample")
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoResponse> findByExample(PedidoInput pedidoInput, @PageableDefault(size = 5) Pageable pageable) {
        var pedido = mapper.toEntity(pedidoInput);
        pedido.setSubTotal(null);
        pedido.setValorTotal(null);
        pedido.setId(null);
        pedido.setCliente(null);

        return mapper.toPage(service.findByExample(pedido, pageable));
    }

    @GetMapping("/fields")
    @ResponseStatus(HttpStatus.OK)
    public MappingJacksonValue getAll(@RequestParam(required = false) String fields) {

        String[] fieldsArray = null;

        if (StringUtils.isNotBlank(fields)) {
            fieldsArray = fields.split(",");

            Arrays.stream(fieldsArray).forEach(field -> {
                if (ReflectionUtils.findField(PedidoResponse.class, field) == null) {
                    throw new FieldValidationException(
                            String.format("Field %s, inválida", field));
                }
            });
        }


        Collection<PedidoResponseFilter> pedidoResponseFilter =
                pedidoResponseFilterMapper.toCollection(service.findAllEager());

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(pedidoResponseFilter);

        SimpleFilterProvider filter = new SimpleFilterProvider();

        if (fieldsArray != null) {
            filter.addFilter("pedidoFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(
                            fieldsArray));
        } else {
            filter.addFilter("pedidoFilter",
                    SimpleBeanPropertyFilter.serializeAll());
        }

        mappingJacksonValue.setFilters(filter);

        return mappingJacksonValue;
    }

    @GetMapping("{pedidoId}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponse getById(@PathVariable Long pedidoId) {

        SimpleFilterProvider filter = new SimpleFilterProvider();
        filter.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());

        return mapper.toModel(service.findById(pedidoId));
    }

    @GetMapping(params = "pedido")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponse getByCodigo(@RequestParam("pedido") @NotBlank String codigoPedido) {
        return mapper.toModel(service.findByCodigo(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse add(@RequestBody @Valid PedidoInput pedidoInput) {
        Pedido pedido = mapper.toEntity(pedidoInput);
        return mapper.toModel(service.cadastrarPedido(pedido));
    }

    @PatchMapping("{pedidoId}/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarPedido(@PathVariable Long pedidoId) {
        service.cancelarPedido(pedidoId);
    }

    @PatchMapping("{pedidoId}/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarPedido(@PathVariable Long pedidoId) {
        service.confirmarPedido(pedidoId);
    }

    @PatchMapping("{pedidoId}/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmarEntregaPedido(@PathVariable Long pedidoId) {
        service.confirmarEntregaPedido(pedidoId);
    }
}
