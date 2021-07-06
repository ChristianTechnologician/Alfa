<%@ page import="Model.Ordine.Ordine" %>
<%@ page import="java.util.List" %>
<%List<Ordine> ordini = (List<Ordine>)request.getAttribute("ordine");%>
<table>
    <caption>Lista Ordini</caption>
    <thead>
    <tr>
        <th>Numero fattura</th>
        <th>Via</th>
        <th>Numero civico</th>
        <th>Città</th>
        <th>Provincia</th>
        <th>Prezzo totale</th>
        <th>Data fattura</th>
        <th>Stato</th>
        <th>Codice merce</th>
        <th>Id utente</th>
    </tr>
    </thead>
    <tbody>
        <%if(ordini == null){%>
    <tr>
        <td>Nessun ordine presente</td>
    </tr>
        <%}else{%>
        <%
        for(Ordine ordine : ordini){
    %>
    <tr>
        <td data-head="Numero fattura"><%=ordine.getNumeroFattura()%></td>
        <td data-head="Via"><%=ordine.getVia()%></td>
        <td data-head="Numero civico"><%=ordine.getCivico()%></td>
        <td data-head="Città"><%=ordine.getCitta()%></td>
        <td data-head="Provincia"><%=ordine.getProvincia()%></td>
        <td data-head="Prezzo totale"><%=ordine.getPrezzoTotale()%></td>
        <td data-head="Data fattura"><%=ordine.getDate()%></td>
        <td data-head="Stato"><%=ordine.getStato()%></td>
        <td data-head="Codice merce"><%=ordine.getCodiceMerceAcquistata()%></td>
        <td data-head="Id utente"><%=ordine.getIdUtente()%></td>
    </tr>
        <%}%>
        <%}%>
    </tbody>
</table>