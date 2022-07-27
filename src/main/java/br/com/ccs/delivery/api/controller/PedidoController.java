package br.com.ccs.delivery.api.controller;

import br.com.ccs.delivery.api.model.representation.input.PedidoInput;
import br.com.ccs.delivery.api.model.representation.response.PedidoResponse;
import br.com.ccs.delivery.api.model.representation.response.filter.PedidoResponseFilter;
import br.com.ccs.delivery.core.exception.FieldValidationException;
import br.com.ccs.delivery.core.mapper.MapperInterface;
import br.com.ccs.delivery.core.mapper.PedidoResponseFilterMapper;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifier;
import br.com.ccs.delivery.core.mapperanotations.MapperQualifierType;
import br.com.ccs.delivery.domain.model.entity.Pedido;
import br.com.ccs.delivery.domain.model.specification.filter.PedidoFilter;
import br.com.ccs.delivery.domain.service.PedidoService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("api/pedidos")
@AllArgsConstructor
public class PedidoController {

    private PedidoService service;
    @MapperQualifier(MapperQualifierType.PEDIDO)
    private MapperInterface<PedidoResponse, PedidoInput, Pedido> mapper;
    private PedidoResponseFilterMapper pedidoResponseFilterMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoResponse> getAll(Pageable pageable) {
        return mapper.toPage(service.findAllEager(pageable));
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<PedidoResponse> filter(PedidoFilter pedidoFilter, @PageableDefault(size = 5) Pageable pageable) {
        return mapper.toPage(service.filter(pedidoFilter, pageable));
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

        return mapper.toResponseModel(service.findById(pedidoId));
    }

    @GetMapping(params = "pedido")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponse getByCodigo(@RequestParam("pedido") @NotBlank String codigoPedido) {
        return mapper.toResponseModel(service.findByCodigo(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse add(@RequestBody @Valid PedidoInput pedidoInput) {
        Pedido pedido = mapper.toEntity(pedidoInput);
        return mapper.toResponseModel(service.cadastrarPedido(pedido));
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
