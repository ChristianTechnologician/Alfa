<%@ page import="Model.Fornitura.Fornitura" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Merce> list=(List<Merce>) request.getAttribute("merce");%>

<table>
    <caption>Lista Merce</caption>
    <thead>
    <tr>
        <th>Codice</th>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Genere</th>
        <th>Prezzo</th>
        <th>Tipo categoria</th>
        <th>Sconto</th>
    </tr>
    </thead>
    <tbody>
    <%
    for(Merce merce : list){
    %>
    <tr>
        <td data-head="Codice"><a href="${pageContext.request.contextPath}/merce/dettaglio"><%=merce.getCodice()%></a></td>
        <td data-head="Nome"><%=merce.getNome()%></td>
        <td data-head="Descrizione"><%=merce.getDescrizione()%></td>
        <td data-head="Genere"><%=merce.getGenere()%></td>
        <td data-head="Prezzo"><%=merce.getPrezzo()%></td>
        <td data-head="Tipo categoria"><%=merce.getTipocategoria()%></td>
        <td data-head="Sconto"><%=merce.getSconto()%></td>
    </tr>
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
