<%@ page import="Model.Merce.Merce" %>
<%@ page import="Model.Fornitura.Fornitura" %>
<%@ page import="Model.Utente.UtenteSession" %>
<%@ page import="Model.Preferiti.PreferitiSession" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Colore.Colore" %>
<%@ page import="Model.Taglia.Taglia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Dettaglio"/>
        <jsp:param name="style" value="reset,libraryCustomer"/>
        <jsp:param name="script" value="ajaxDettaglio"/>
    </jsp:include>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<%
    PreferitiSession preferitiSession = (PreferitiSession)request.getSession().getAttribute("preferiti");
    List<Fornitura>fornituras = (List<Fornitura>)request.getAttribute("forniture");
    List<Colore>colores = (List<Colore>)request.getAttribute("colori");
    Merce prodotto = (Merce) request.getAttribute("prodotto");
%>
<header>
<%@include file="/WEB-INF/views/partials/HeaderCustomer.jsp"%>
</header>
<section>
<div class="row">
    <div class="column" style="float:left;width:40%">
        <ul>
            <li style="float: left "> <div class="gallery">
                        <img src="./Images/<%=prodotto.getCodice()%>.jpg" alt="img" width="200" height="100">
                    <div class="desc"><%=prodotto.getNome()%></div>
            </div></li>
        </ul>
    </div>
</div>
    <div class="row">
        <div class="column" style="float:right;width:60%">
            <ul>
                <li style="float: right "> <div class="gallery">
                    <div class="descrizione"><h3>Descrizione</h3><br><%=prodotto.getDescrizione()%></div>
                    <div id="FornituraWrapper">Fornitura
                        <select id="Fornitura">
                        <%int i = 0;
                            for (Fornitura f:fornituras) {
                                for (Colore c:colores) {
                                if(f.getCodColore()==c.getCod()){%>
                                <option name="<%=i%>" value="<%=f.getIdentificatore()%>">Taglia: <%=f.getlTaglia()%>  Colore: <%=c.getTipoColore()%></option>
                            <%}}i++;}%>
                    </select>
                    </div>
                    <p>Quantita</p>
                    <label id="quantitaWrapper">
                        <input type="number" name="quantita" id="quantita" placeholder="Num" min="1" max="10">
                    </label>
                    <div>Prezzo:€<%=prodotto.getPrezzo()%></div>
                    <div>Sconto:<%=prodotto.getSconto()%></div>
                    <%String x = ""+prodotto.getCodice()+","+preferitiSession.getIdUtente()+","+preferitiSession.getRegistrato();%>
                    <td><button name="aggiungiPreferiti" id="aggiungiP"  onclick="aggiungiPreferiti('<%=x%>')">Aggiungi ai preferiti</button></td>
                    <td><button name="aggiungiCarrello" id="aggiungiC"  onclick="aggiungiCarrello('<%=x%>')">Aggiungi al carrello</button></td>
                </div></li>
            </ul>
        </div>
    </div>
</section>
<footer>
 <%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</footer>
</body>
</html>
