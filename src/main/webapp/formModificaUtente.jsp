<%@ page import="Model.Utente.Utente" %>
<%@ page import="Model.Utente.UtenteSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%UtenteSession profileU = (UtenteSession) request.getSession().getAttribute("accountSession");%>
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
    <%if(profileU == null){%>
    <tr>
        <td>Non ci sono utenti corrispondenti</td>
    </tr>
    <%}else{
    %>
    <tr>
        <td data-head="Id"><%=profileU.getId()%></td>
        <td data-head="Nome"><%=profileU.getFirstName()%></td>
        <td data-head="Cognome"><%=profileU.getLastName()%></td>
        <td data-head="Email"><%=profileU.getEmail()%></td>
    </tr>
    <%}%>
    </tbody>
</table>
<button type="button" id="b" onclick="Modifica()">Modifica</button>
<div id="modifica" style="display: none"><%@include file="/WEB-INF/views/customer/updateProfile.jsp"%></div>
