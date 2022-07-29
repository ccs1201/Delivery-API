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

function preencherTabela(tiposPagamento) {
    $("#tabela tbody tr").remove();

    $.each(tiposPagamento, function (i, pagamento) {
        var linha = $("<tr>");

        var linkAcao = $("<a href='#'>")
            .text("Excluir")
            .click(function (event) {
                event.preventDefault();
                excluirTipoPagamento(pagamento);
            });

        linha.append(
            $("<td>").text(pagamento.id),
            $("<td>").text(pagamento.nome),
            $("<td>").append(linkAcao)
        );

        linha.appendTo("#tabela");
    });
}

function excluirTipoPagamento(pagamento) {
    $.ajax({
        url: "http://localhost:8080/api/tipos-pagamento/" + pagamento.id,
        type: "delete",

        success: function (response) {
            getTiposPagamento();
            alert("Tipo de pagamento removido com sucesso.")
        },

        error: function (error) {
            if (error.status >= 400 && error.status <= 499) {
                var problem = JSON.parse(error.responseText);
                alert(problem.detail);
            } else {
                alert("Erro desconhecido.");
            }
        }
    })
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
            alert("Tipo de pagamento adicionada!");
            getTiposPagamento();
        },

        error: function (error) {
            if (error.status >= 400 && error.status <= 499) {
                var problem = JSON.parse(error.responseText);
                alert(problem.detail);
            } else {
                alert("Erro ao cadastrar tipo de pagamento!");
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