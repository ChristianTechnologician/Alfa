<%@ page import="Model.Merce.Merce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-DeleteMerce"/>
        <jsp:param name="style" value="crm"/>
        <jsp:param name="script" value="crm"/>
    </jsp:include>
</head>
<body>
<header class="grid-x justify-center">
    <img src="${pageContext.request.contextPath}/Images/LOGO.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">
</header>

<ul>
    <div class="dropdown grid-x align-center">
        <li><a href="/crm/merce">Gestione Merce</a>
            <div class="dropdown-content">
                <a href="./crm/showMerce">Visualizza Merce</a>
                <a href="./crm/insertMerce">Inserisci Merce</a>
                <a href="./crm/updateMerce">Modifica Merce</a>
                <a href="./crm/deleteMerce">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="">Gestione Ordine </a>
            <div class="dropdown-content">
                <a href="">Visualizza Ordini</a>
                <a href="">Inserisci Ordine</a>
                <a href="">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="">Gestione Utente</a>
            <div class="dropdown-content">
                <a href="">Visualizza Utenti</a>
                <a href="">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="">Profilo</a>
        </li>
    </div>

    <div class=" dropdown grid-x align-center">
        <li>
            <a href="">Esci</a>
        </li>
    </div>

</ul>

Inserisci il codice del prodotto che vuoi modificare

<form action="" method="post">
    <label id="#updatecodice">
        <input type="text" name="IdMerce" placeholder="ID">
    </label>
</form>

<%Merce m = (Merce) session.getAttribute("merce"); %>

<section class="field">
    <form action="" method="post">
        <label id="#nome">
            <input type="text" name="NomeMerce" placeholder="Nome">
        </label>
        <label id="#descrizione">
            <input type="text" name="DescrizioneMerce" placeholder="Descrizione">
        </label>
        <label id="#genere">
            <input type="text" name="GenereMerce" placeholder="Genere">
        </label>
        <label id="#prezzo">
            <input type="number" name="PrezzoMerce" placeholder="Prezzo">
        </label>
        <label id="#tipoCategoria">
            <input type="text" name="TipoCategoria" placeholder="Categoria(Completo,Cappotto)">
        </label>
        <label id="#sconto">
            <input type="number" name="Sconto" placeholder="%sconto">
        </label>
        <label id="#colore">
            <input type="text" name="Colore" placeholder="Colore">
        </label>
        <label id="#taglia">
            <input type="text" name="Taglia" placeholder="Taglia">
        </label>
        <label id="#quantita">
            <input type="number" name="Quantita" placeholder="Quantità">
        </label>
    </form>
</section>

<%boolean operazione = (boolean) session.getAttribute("inserimento"); %>
<% if(operazione){ %>
Inserimento riuscito
<% }else{%>
Inserimento fallito
<%}%>
</body>
</html>
