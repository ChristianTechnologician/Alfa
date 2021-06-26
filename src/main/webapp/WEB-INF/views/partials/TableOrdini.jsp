<%@ page import="Model.Ordine.Ordine" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");%>

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
        <td data-head="Id utente"><%=ordine.getIdUtente()%></td>
    </tr>
    <%}%>
    <%}%>
    <c:choose>
        <c:when test="${list.isEmpty()}">
            <tr>
                <td>Nessuna merce presente</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${list}" var="merce">
                <tr>
                    <td data-head="Codice">${merce.id}</td>
                    <td data-head="Nome">${merce.nome}</td>
                    <td data-head="Descrizione">${merce.descrizione}</td>
                    <td data-head="Genere">${merce.genere}</td>
                    <td data-head="Prezzo">${merce.prezzo}</td>
                    <td data-head="Tipo categoria">${merce.categoria}</td>
                    <td data-head="Sconto">${merce.sconto}</td>
                    <td data-head="Colore">${merce.colore}</td>
                    <td data-head="Taglia">${merce.taglia}</td>
                    <td data-head="Quantità">${merce.quantita}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
