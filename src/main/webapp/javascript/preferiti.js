function aggiungi(s){
    const array=s.split(",")
    var aggiunta = {
        codiceM: array[0],id: array[1]  ,fcodice: array[2], quantita: array[3],registrato: array[4]
    };
    alert(array[2])
    /*var aggiunta = {
        codiceM: array[0],id: array[1]  ,fcodice: array[2],registrato: array[3]
    };*/
    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war_exploded/preferiti/aggiungiCarrello",
        contentType: "JSON", // NOT dataType!
        data:{ aggiunta: JSON.stringify(aggiunta)},
        success: function(response) {
            alert("Aggiunto con successo");
        },
        error: function(response) {
            alert('Aggiunta non riuscita');
        }
    });
}

function rimuovi(s, i){
    //var s = $("#remove").val();
    //var y=i;
    const array=s.split(",")
    var rimozione = {
        codiceM: array[0],id: array[1]  ,fcodice: array[2], quantita: array[3],registrato: array[4]
    };
    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war_exploded/preferiti/rimuovi",
        contentType: "JSON", // NOT dataType!
        data:{ rimozione: JSON.stringify(rimozione)},
        success: function(response) {
           // $('tr').each()
                $("#"+i).remove()
        },
        error: function(response) {
            alert('Rimozione non riuscita');
        }
    });
}