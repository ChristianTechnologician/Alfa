function rimuovi(s, i){
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
                $("#"+i).remove()
        },
        error: function(response) {
        alert('Rimozione non riuscita');
    }
    });

    function controllo(c){
        if(c===0){
            alert("Registrazione o login necessaria per aquistare");
        }
    }

}



/*<%for(Fornitura f : forniture){
    if(f.getCodMerce().equals(merci.get(i).getCodice())){
        if(f.getIdentificatore()==cs.Fcodice().get(i)) {
            //for(int q=0;q<cs.Quantita().size();q++){}
            //if(cs.mCodice().get(q).equals(merci.get(i).getCodice())){
            <%id=cs.getIDutente();%>
                <% s =cs.Quantita().get(i);break;}%>
    <%}%>
<%}%>*/