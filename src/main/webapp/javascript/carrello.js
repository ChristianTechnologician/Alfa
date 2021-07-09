function rimuovi(){
    var s = $("#remove").val();
    const array=s.split(",")
    var rimozione = {
        codiceM: array[0],id: array[1]  ,fcodice: array[2]
    };

    alert(x);

    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war/carrello/rimuovi",
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