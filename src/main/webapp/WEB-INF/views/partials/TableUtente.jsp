<%@ page import="Model.Utente.Utente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Utente> utenti = (List<Utente>) request.getAttribute("utente");%>

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
    <%if(utenti == null){%>
    <tr>
        <td>Non ci sono utenti corrispondenti</td>
    </tr>
    <%}else{
        for(Utente utente : utenti){
    %>
    <tr>
        <td data-head="Id"><%=utente.getId()%></td>
        <td data-head="Nome"><%=utente.getNome()%></td>
        <td data-head="Cognome"><%=utente.getCognome()%></td>
        <td data-head="Email"><%=utente.getEmail()%></td>
    </tr>
    <%}%>
    <%}%>
    </tbody>
</table>
