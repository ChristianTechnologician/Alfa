<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Fornitura.Fornitura" %>
<%@ page import="Model.Colore.Colore" %>
<%@ page import="Model.Carrello.CarrelloSession" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Merce> merci = (List<Merce>) request.getAttribute("carrelloMerce");%>
<%CarrelloSession cs = (CarrelloSession) request.getSession().getAttribute("carrello");
List<Integer> cod = cs.Fcodice();%>
<%List<Fornitura> forniture = (List<Fornitura>) request.getAttribute("carrelloFornitura");%>
<%List<Colore> colori = (List<Colore>) request.getAttribute("carrelloColori");%>
<%double prezzoTotale=0;%>
<%
    for (Merce m: merci)
    {
        prezzoTotale+=m.getPrezzo();
    }%>
<div class="row">
    <div class="column" style="float:left; width: 30%">
<table>
    <caption>Lista della merce nel carrello</caption>
    <thead>
    <%if(forniture !=null){ %>
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
    <%}else{%>
    <tr>
        <th>Immagine</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Genere</th>
        <th>Prezzo</th>
    </tr>
    <%}%>
    </thead>
    <tbody>
    <%if(merci == null ){%>
    <tr>
        <td>Non ci sono ancora merci nel carrello</td>
    </tr>
    <%}else{
        for(int i = 0;i<merci.size();i++){
            String code ="";
            int id=0;
            int fcodice=0;
            int s=0;
    %>
    <tr id="<%=i%>">
        <td data-head="Immagine"><img src="../Images/<%=merci.get(i).getCodice()%>.jpg" alt="img" width="200" height="150"></td>
        <td data-head="Nome"><form action="${pageContext.request.contextPath}/DettaglioServlet" method="get"><button name="prodotto" id="prodotto" value="<%=merci.get(i).getCodice()%>"><%=merci.get(i).getNome()%></button></form></td>
        <td data-head="Categoria"><%=merci.get(i).getTipocategoria()%></td>
        <td data-head="Genere"><%=merci.get(i).getGenere()%></td>
        <%if(forniture == null){ %>
        <td data-head="Prezzo"><%=merci.get(i).getPrezzo()%></td>
        </tr>
        <%}else{
            String c ="";
            for(Fornitura f : forniture){
        %>
        <%if(f.getCodMerce().equals(merci.get(i).getCodice())){
            for(Colore color : colori){
                if(f.getCodColore()==color.getCod()){
                    c =color.getTipoColore();
                    break;
                }
            }%>
        <%break;
        }%>
        <%}%>
    <td data-head="Colore"><%=c%></td>

    <%for(Fornitura f : forniture){%>
        <%if(f.getCodMerce().equals(merci.get(i).getCodice())){%>
        <%for(int codice: cod){
        if(f.getIdentificatore()==codice){%>
    <%fcodice=codice;%>
    <% c = f.getlTaglia();break;}%>
        <%}%>
    <%break;}%>
    <%}%>
    <td data-head="Taglia"><%=c%></td>

    <%for(int q=0;q<cs.Quantita().size();q++){
    if(cs.mCodice().get(q).equals(merci.get(i).getCodice())){%>
     <%id=cs.getIDutente();%>
    <% s =cs.Quantita().get(q);break;}%><%}%>

    <td data-head="Quantità"><%=s%></td>

    <td data-head="Prezzo"><%=merci.get(i).getPrezzo()%></td>
    <%}%>
    <%String x = ""+merci.get(i).getCodice()+","+id+","+fcodice+","+s+"";%>
    <td><button name="rimuovi" id="remove" value="<%=x%>" onclick="rimuovi('<%=x%>','<%=i%>', '<%=merci.get(i).getPrezzo()%>','<%=prezzoTotale%>')">Rimuovi</button></td>
    <td></td>
    </tr>
    <%}%>
    <%}%>
    </tbody>
</table>
    </div>
</div>

<div class="row">
    <div class="column" style="float:right; width: 30%" >
    <h2>Procedi all'ordine</h2>
    <h3>Prezzo Totale</h3>
        <h2 id="prezzototale">€<%=prezzoTotale%></h2>
        <form action="${pageContext.request.contextPath}/carrello/ordine" method="get"><button>Procedi all'acquisto</button></form>
    </div>
</div>