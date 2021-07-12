function aggiungiPreferiti(s){
    let q  = $("#quantita").val();
    let c = $("#Fornitura").val();
    const array=s.split(",")
    var aggiunta = {
        codiceM: array[0],id: array[1]  ,fcodice: c, quantita: q,registrato: array[2]
    };
    alert(q)
    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war_exploded/preferiti/aggiungiPreferiti",
        contentType: "JSON", // NOT dataType!
        data:{ aggiunta: JSON.stringify(aggiunta)},
        success: function(response) {
            alert("Merce aggiunta ai preferiti con successo")
        },
        error: function(response) {
            alert('Aggiunta ai preferiti non riuscita');
        }
    });
}

function aggiungiCarrello(s){
    let q  =  $("#quantita").val();
    let c = $("#Fornitura").val();
    const array=s.split(",")
    var aggiunta = {
        codiceM: array[0],id: array[1]  ,fcodice: c, quantita: q,registrato: array[2]
    };
    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war_exploded/carrello/aggiungi",
        contentType: "JSON", // NOT dataType!
        data:{ aggiunta: JSON.stringify(aggiunta)},
        success: function(response) {
            alert("Aggiunta al carrello avvenuta con successo")
        },
        error: function(response) {
            alert('Aggiunta al carrello non riuscita');
        }
    });
}
