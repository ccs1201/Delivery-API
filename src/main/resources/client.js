function consultarRestaurantes() {
    $.ajax({
        url: "http://localhost:8080/api/restaurantes",
        type: "get",

        success: function (response) {
            $("#conteudo").text(JSON.stringify(response));
        }
    });

}

function fecharRestaurante() {
    $.ajax({
        "url": "http://localhost:8080/api/restaurantes/1/fechado",
        "type": "patch",

        "success": function () {
            alert("Restaurante Fechado com sucesso.");
            clearConteudo();
        },

        "statusCode": {
            404: function (response) {
                alert("Não foi possível fechar o restaurante.");
                $("#conteudo").text(JSON.stringify(response));
            }
        }

    });
}

function abrirRestaurante() {
    $.ajax({
        url: "http://localhost:8080/api/restaurantes/1/aberto",
        type: "patch",

        success: function () {
            alert("Restaurante Aberto com sucesso");
            clearConteudo();

        },
        "statusCode": {
            404: function (response) {
                alert("Não foi possível abrir o restaurante.");
                $("#conteudo").text(JSON.stringify(response));
            }
        }
    });
}


function getTiposPagamento() {
    $.ajax({
        url: "http://localhost:8080/api/tipos-pagamento",
        type: "get",

        success: function (response) {
            preencherTabela(response);
        }
    });
}

function preencherTabela(formasPagamento) {
    $("#tabela tbody tr").remove();

    $.each(formasPagamento, function (i, formaPagamento) {
        var linha = $("<tr>");

        linha.append(
            $("<td>").text(formaPagamento.id),
            $("<td>").text(formaPagamento.nome)
        );

        linha.appendTo("#tabela");
    });
}

function cadastrarTipoPagamento() {
    var formaPagamentoJson = JSON.stringify({
        "nome": $("#txtDescricao").val()
    });

    console.log(formaPagamentoJson);

    $.ajax({
        url: "http://localhost:8080/api/tipos-pagamento",
        type: "post",
        data: formaPagamentoJson,
        contentType: "application/json",

        success: function (response) {
            alert("Forma de pagamento adicionada!");
            consultar();
        },

        error: function (error) {
            if (error.status == 409) {
                var problem = JSON.parse(error.responseText);
                alert(problem.detail);
            } else {
                alert("Erro ao cadastrar forma de pagamento!");
            }
        }
    });
}

function clearConteudo() {
    $("#conteudo").text("");
}

$("#botao").click(consultarRestaurantes);
$("#btnFechar").click(fecharRestaurante);
$("#btnAbrir").click(abrirRestaurante);
$("#btn-consultar").click(getTiposPagamento);
$("#btnCadastrar").click(cadastrarTipoPagamento);