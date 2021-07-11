//$( "#button" ).click(function() {
/*function ordini() {
    // $.ajax({
    //$("#button").onclick.toggle(
        $.ajax({
            // definisco il tipo della chiamata
            type: "GET",
            // specifico la URL della risorsa da contattare
            url: "http://localhost:8080/Alfa_war/ordiniUtente.jsp",
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
}*/


function ordini(){
    var v = document.getElementById("ordini");
    if(v.style.display === "block"){
        v.style.display = "none";
    }else{
        v.style.display = "block";
    }
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
            alert("Problema del server." +
                "Riprova più tardi!");
        }
    });
}

function elimina() {

   /* var v = document.getElementById("elimina");
    if (v.style.display === "block") {
        v.style.display = "none";
    } else {
        v.style.display = "block";
    }

        Alert.render('You look very pretty today.');

        var Alert = new CustomAlert();

        function CustomAlert() {
            this.render = function () {
                //Show Modal
                let popUpBox = document.getElementById('popUpBox');
                popUpBox.style.display = "block";
                //Close Modal
                document.getElementById('closeModal').innerHTML = '<button onclick="Alert.ok()">OK</button>';
            }

            this.ok = function () {
                document.getElementById('popUpBox').style.display = "none";
                document.getElementById('popUpOverlay').style.display = "none";
            }
        }*/

    $.ajax({
        // definisco il tipo della chiamata
        type: "GET",
        // specifico la URL della risorsa da contattare
        url: "http://localhost:8080/Alfa_war_exploded/Question.jsp",
        // passo dei dati alla risorsa remota
        //data: "Alert.render('You look very pretty today.')",
        // definisco il formato della risposta
        dataType: "html",
        // imposto un'azione per il caso di successo
        success: function (risposta) {
            $("#central").html(risposta);
        },
        // ed una per il caso di fallimento
        error: function () {
            alert("Problema del server." +
                "Riprova più tardi!");
        }
    });

}
