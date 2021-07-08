function validateInsert(form)
{
    //let email = document.getElementById('email').value;
    let codice = $("#Codice").val();
    console.log(codice);
    let nome = $("#Nome").val();
    console.log(nome);
    let descrizione = $("#Descrizione").val();
    console.log(descrizione);
    /*let genere = $("#Genere").val();
    console.log(genere);*/
    let prezzo = $("#Prezzo").val();
    console.log(prezzo);
    /*let tipoCategoria = $("#TipoCategoria").val();
    console.log(tipoCategoria);*/
    let sconto = $("#Sconto").val();
    console.log(sconto);
    //let c_pattern =;
    let n_pattern =/^[a-z ,.'-]+$/i ;
    let d_pattern =/^([A-Z])([\w|\W|\s]{250})+$/;
    //let g_pattern = ;
    let p_pattern =/^([\d\.\d\d]|[1-9\d\.\d\d]|[1-9]\d\d\.\d\d]|[1-9\d\d\d\.\d\d])+$/;
    //let t_pattern = ;
    let s_pattern =/^(0|[1-9\d])+$/ ;
    if(!codice.match(c_pattern)){
        $("#Codice").css("background-color", "rgba(255, 0, 0, 0.5)");
        alert("Dati codice errati");
        return false;
    }
    if(!nome.match(n_pattern)){
        $("#Nome").css("background-color", "rgba(255, 0, 0, 0.5)");
        alert("Dati nome errati");
        return false;
    }
    if(!descrizione.match(d_pattern)){
        $("#Descrizione").css("background-color", "rgba(255, 0, 0, 0.5)");
        alert("Dati descrizione errati");
        return false;
    }
    if(!prezzo.match(p_pattern)){
        $("#Prezzo").css("background-color", "rgba(255, 0, 0, 0.5)");
        alert("Dati prezzo errati");
        return false;
    }
    if(!sconto.match(s_pattern)){
        $("#Sconto").css("background-color", "rgba(255, 0, 0, 0.5)");
        alert("Dati sconto errati");
        return false;
    }
    form.submit();
}