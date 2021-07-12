<%@ page import="Model.Fornitura.Fornitura" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Merce.Merce" %>
<%@ page import="Model.Colore.Colore" %>

<%Merce list=(Merce) request.getAttribute("merce");%>
<%List<Fornitura> forniture =(List<Fornitura>) request.getAttribute("fornitura");%>
<%List<Colore> colori =(List<Colore>) request.getAttribute("colore");%>

<table>
    <caption>Dettaglio Merce</caption>
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
    <tr>
        <td data-head="Codice"><%=list.getCodice()%></td>
        <td data-head="Nome"><%=list.getNome()%></td>
        <td data-head="Descrizione"><%=list.getDescrizione()%></td>
        <td data-head="Genere"><%=list.getGenere()%></td>
        <td data-head="Prezzo"><%=list.getPrezzo()%></td>
        <td data-head="Tipo categoria"><%=list.getTipocategoria()%></td>
        <td data-head="Sconto"><%=list.getSconto()%></td>
    </tr>
    </tbody>
</table>

<table>
    <caption>Fornitura <%=list.getCodice()%></caption>
    <thead>
    <tr>
        <th>Codice</th>
        <th>Colore</th>
        <th>Taglia</th>
        <th>Quantità</th>
    </tr>
    </thead>
    <tbody>
    <%if(forniture==null){%>
    <tr> Non ci sono forniture per la merce seguente</tr>
    <%}else{
        for(Fornitura fornitura : forniture){
    %>
    <tr>
        <td data-head="Codice"><%=fornitura.getCodMerce()%></td>
        <% String color ="";
        for (Colore c :colori ) {
            if(c.getCod()==fornitura.getCodColore()){
                color = c.getTipoColore();
                break;
            }
        }
        %>
        <td data-head="Colore"><%=color%></td>
        <td data-head="Taglia"><%=fornitura.getlTaglia()%></td>
        <td data-head="Quantità"><%=fornitura.getQuantita()%></td>
    </tr>
    <%}%>
    <%}%>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/fornitura/invio" method="get"><button class="premi">+<input type ="hidden" name="Codice" value="<%=list.getCodice()%>"></button></form>
<form action="${pageContext.request.contextPath}/merce/updateMerce" method="get"><button class="premi">Modifica<input type ="hidden" name="IdMerce" value="<%=list.getCodice()%>"></button></form>
<form action="${pageContext.request.contextPath}/merce/deleteMerce" method="post"><button class="premi">Elimina<input type ="hidden" name="IdMerce" value="<%=list.getCodice()%>"></button></form>