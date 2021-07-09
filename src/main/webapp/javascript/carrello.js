function rimuovi(){
    var s = $("#prodotto").val();
    var q = $("#idUtente").val();
    var x = $("#fcodice").val();
    var rimozione = {
        codiceM: s,id: q  ,fcodice: x
    };

    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war_exploded/carrello/rimuovi",
        contentType: "JSON", // NOT dataType!
        data:{ rimozione: JSON.stringify(rimozione)},
        success: function(response) {
            $(this).parent().parent().remove();
        },
        error: function(response) {
        alert('Rimozione non riuscita');
    }
    });
}