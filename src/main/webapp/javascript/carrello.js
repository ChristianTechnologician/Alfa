function rimuovi(s, i, n, q){
    //var s = $("#remove").val();
    //var y=i;
    const array=s.split(",")
    var rimozione = {
        codiceM: array[0],id: array[1]  ,fcodice: array[2], quantita: array[3]
    };
    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war_exploded/carrello/rimuovi",
        contentType: "JSON", // NOT dataType!
        data:{ rimozione: JSON.stringify(rimozione)},
        success: function(response) {
            if(i===0){
                $("#modifica").html("Non ci sono elementi nel carrello")
            }else{
                $("#"+i).remove()
            }
           /* var result=q-n;
            var w = document.getElementById("prezzototale").nodeValue
            alert(w)
            if(w===q)
            {
                $("#prezzototale").html(result)
                alert(result)
            }
            else
            {
                var u=w-n
                alert(u)
                $("#prezzototale").html(u)
            }*/

        },
        error: function(response) {
        alert('Rimozione non riuscita');
    }
    });
}