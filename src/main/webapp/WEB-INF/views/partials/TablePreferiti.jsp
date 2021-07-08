<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Merce> merci = (List<Merce>) request.getAttribute("preferiti");%>

<table>
    <caption>Lista dei preferiti</caption>
    <thead>
    <tr>
        <th>Immagine</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Prezzo</th>
    </tr>
    </thead>
    <tbody>
    <%if(merci == null){%>
    <tr>
        <td>Non ci sono ancora preferiti</td>
    </tr>
    <%}else{
        for(Merce merce : merci){
    %>
    <tr>
        <td data-head="Immagine"><img src="./Images/<%=merce.getCodice()%>.jpg" alt="img" width="700" height="500"></td>
        <td data-head="Nome"><%=merce.getNome()%></td>
        <td data-head="Categoria"><%=merce.getTipocategoria()%></td>
        <td data-head="Prezzo"><%=merce.getPrezzo()%></td>
    </tr>
    <%}%>
    <%}%>
    </tbody>
</table>
