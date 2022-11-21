package br.com.ccs.delivery.api.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @GetMapping
    public RootEntryPointModel root() {

        var root = new RootEntryPointModel();

        root.add(linkTo(ProdutoController.class).withRel("produtos"));
        root.add(linkTo(CozinhaController.class).withRel("cozinhas"));
        root.add(linkTo(RestauranteController.class).withRel("restaurantes"));
        root.add(linkTo(EstadoController.class).withRel("estados"));
        root.add(linkTo(MunicipioController.class).withRel("municipios"));
        root.add(linkTo(PedidoController.class).withRel("pedidos"));
        root.add(linkTo(UsuarioController.class).withRel("usuarios"));
        root.add(linkTo(GrupoController.class).withRel("grupos"));
        root.add(linkTo(PermissaoController.class).withRel("grupo-permissoes"));
        root.add(linkTo(TipoPagamentoController.class).withRel("tipos-pagamento"));
        root.add(linkTo(EstatisticasController.class).withRel("estatisticas"));

        return root;

    }

    private static final class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

}
