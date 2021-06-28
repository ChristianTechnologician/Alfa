<%@ page import="Model.Utente.Utente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Utente> admin = (List<Utente>) request.getAttribute("admin");%>

<table>
    <caption>Amministratori</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(Utente ad : admin){
    %>
    <tr>
        <td data-head="Id"><%=ad.getId()%></td>
        <td data-head="Nome"><%=ad.getNome()%></td>
        <td data-head="Cognome"><%=ad.getCognome()%></td>
        <td data-head="Email"><%=ad.getEmail()%></td>
    </tr>
    <%}%>
    </tbody>
</table>
