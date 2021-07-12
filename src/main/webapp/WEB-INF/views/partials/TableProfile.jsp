<%@ page import="Model.Utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Utente profileU = (Utente) request.getAttribute("profileAdmin");%>
<table>
<caption>Admin</caption>
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
    <td>Non ci  sono utenti corrispondenti</td>
</tr>
<%}else{
%>
<tr>
    <td data-head="Id"><%=profileU.getId()%></td>
    <td data-head="Nome"><%=profileU.getNome()%></td>
    <td data-head="Cognome"><%=profileU.getCognome()%></td>
    <td data-head="Email"><%=profileU.getEmail()%></td>
</tr>
<%}%>
</tbody>
</table>
    <button class="premi" type="button" id="b" onclick="Modifica()">Modifica</button>
<div id="modifica" style="display: none"><%@include file="/WEB-INF/views/crm/updateProfile.jsp"%></div>
