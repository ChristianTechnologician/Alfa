<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Fornitura.Fornitura" %>
<%@ page import="Model.Colore.Colore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Merce> merci = (List<Merce>) request.getAttribute("carrelloMerce");%>
<%List<Fornitura> forniture = (List<Fornitura>) request.getAttribute("carrelloFornitura");%>
<%List<Colore> colori = (List<Colore>) request.getAttribute("carrelloColori");%>

<table>
    <caption>Lista della merce nel carrello</caption>
    <thead>
    <tr>
        <th>Immagine</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Genere</th>
        <th>Colore</th>
        <th>Taglia</th>
        <th>Quantità</th>
        <th>Prezzo</th>
    </tr>
    </thead>
    <tbody>
    <%if(merci == null){%>
    <tr>
        <td>Non ci sono ancora merci nel carrello</td>
    </tr>
    <%}else{
        for(int i = 0;i<merci.size();i++){
    %>
    <tr>
        <td data-head="Immagine"><img src="./Images/<%=merci.get(i).getCodice()%>.jpg" alt="img" width="700" height="500"></td>
        <td data-head="Nome"><%=merci.get(i).getNome()%></td>
        <td data-head="Categoria"><%=merci.get(i).getTipocategoria()%></td>
        <%for(Fornitura f : forniture){
                int s = 0;
                String c ="";
                for(Colore color : colori){
        %>
        <td data-head="Colore"><%if(f.getCodColore()==color.getCod()){c =color.getTipoColore();};%><%=c%></td>
        <%}%>
            <%for(Merce m : merci){%>
        <td data-head="Taglia"><%if(f.getCodMerce().equals(m.getCodice())){c=f.getlTaglia();};%><%=c%></td>
        <td data-head="Quantità"><%if(f.getCodMerce().equals(m.getCodice())){s=f.getQuantita();};%><%=s%></td>
        <%}%>
        <%}%>
        <td data-head="Prezzo"><%=merci.get(i).getPrezzo()%></td>
    </tr>
    <%}%>
    <%}%>
    </tbody>
</table>
