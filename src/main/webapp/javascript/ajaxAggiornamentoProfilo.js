//$( "#button" ).click(function() {
function ordini() {
    // $.ajax({
    //$("#button").onclick.toggle(
        $.ajax({
            // definisco il tipo della chiamata
            type: "GET",
            // specifico la URL della risorsa da contattare
            url: "http://localhost:8080/Alfa_war_exploded/ordiniUtente.jsp",
            // passo dei dati alla risorsa remota
            //data: "nome=giovanni&cognome=belelli",
            // definisco il formato della risposta
            dataType: "html",
            // imposto un'azione per il caso di successo
            success: function (risposta) {
                $("#central").html(risposta);
            },
            // ed una per il caso di fallimento
            error: function () {
                alert("Chiamata fallita!!!");
            }
        })
   // );
}

function modifica(){
    $.ajax({
        // definisco il tipo della chiamata
        type: "GET",
        // specifico la URL della risorsa da contattare
        url: "http://localhost:8080/Alfa_war_exploded/formModificaUtente.jsp",
        // passo dei dati alla risorsa remota
        //data: "nome=giovanni&cognome=belelli",
        // definisco il formato della risposta
        dataType: "html",
        // imposto un'azione per il caso di successo
        success: function (risposta) {
            $("#central").html(risposta);
        },
        // ed una per il caso di fallimento
        error: function () {
            alert("Chiamata fallita!!!");
        }
    });
}

function elimina(){
    $.ajax({
        // definisco il tipo della chiamata
        type: "GET",
        // specifico la URL della risorsa da contattare
        url: "http://localhost:8080/Alfa_war_exploded/reg.jsp",
        // passo dei dati alla risorsa remota
        //data: "nome=giovanni&cognome=belelli",
        // definisco il formato della risposta
        dataType: "html",
        // imposto un'azione per il caso di successo
        success: function (risposta) {
            $("#central").html(risposta);
        },
        // ed una per il caso di fallimento
        error: function () {
            alert("Chiamata fallita!!!");
        }
    });
}