<%@ page import="Model.Utente.Utente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Utente utente = (Utente) request.getAttribute("utente");%>

<table>
    <caption>Utente</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <%if(utente == null){%>
    <tr>
        <td>Non è stato trovato un utente corrispondente</td>
    </tr>
    <%}else{%>
    <tr>
        <td data-head="Id"><%=utente.getId()%></td>
        <td data-head="Nome"><%=utente.getNome()%></td>
        <td data-head="Cognome"><%=utente.getCognome()%></td>
        <td data-head="Email"><%=utente.getEmail()%></td>
    </tr>
    <%}%>
    </tbody>
</table>
