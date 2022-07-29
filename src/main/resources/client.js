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

        "success": function (response) {
            alert("Restaurante Fechado com sucesso.");
            clearConteudo();
        },

        "statusCode":{
            404: function (response){
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

        success: function (response) {
            alert("Restaurante Aberto com sucesso");
            clearConteudo();

        },
        "statusCode":{
            404: function (response){
                alert("Não foi possível abrir o restaurante.");
                $("#conteudo").text(JSON.stringify(response));
            }
        }
    });
}

function clearConteudo(){
    $("#conteudo").text("");
}

$("#botao").click(consultarRestaurantes);
$("#btnFechar").click(fecharRestaurante);
$("#btnAbrir").click(abrirRestaurante);