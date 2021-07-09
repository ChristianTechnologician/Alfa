function rimuovi(s, i, n, q){
    //var s = $("#remove").val();
    //var y=i;
    const array=s.split(",")
    var rimozione = {
        codiceM: array[0],id: array[1]  ,fcodice: array[2]
    };
    $.ajax({
        type: "get",
        url: "http://localhost:8080/Alfa_war/carrello/rimuovi",
        contentType: "JSON", // NOT dataType!
        data:{ rimozione: JSON.stringify(rimozione)},
        success: function(response) {
            $("#"+i).remove()
            alert(q)
            var result=q-n;
            if($("#prezzototale").html()==q)
            {
                $("#prezzototale").html(result)
            }
            else
            {
                var u=$("#prezzototale").html()-n
                $("#prezzototale").html(u)
            }

        },
        error: function(response) {
        alert('Rimozione non riuscita');
    }
    });
}