function ajax(){
    $.ajax({
        // definisco il tipo della chiamata
        type: "GET",
        // specifico la URL della risorsa da contattare
        //url: "http://localhost:8080/Alfa_war/registrazione.html",
        url: "http://localhost:8080/Alfa_war_exploded/reg.jsp",
        // passo dei dati alla risorsa remota
        //data: "nome=giovanni&cognome=belelli",
        // definisco il formato della risposta
        dataType: "html",
        // imposto un'azione per il caso di successo
        success: function (risposta) {
            $("#ajax").html(risposta);
        },
        // ed una per il caso di fallimento
        error: function () {
            alert("Problema del server." +
                "Riprova più tardi!");
        }
    });
}


