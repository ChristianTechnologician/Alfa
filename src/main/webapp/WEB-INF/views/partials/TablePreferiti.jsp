<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Fornitura.Fornitura" %>
<%@ page import="Model.Colore.Colore" %>
<%@ page import="Model.Preferiti.PreferitiSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Merce> merci = (List<Merce>) request.getAttribute("preferitiMerce");%>
<%PreferitiSession cs = (PreferitiSession) request.getSession().getAttribute("preferiti");
    List<Integer> cod = cs.Fcodice();%>
<%List<Fornitura> forniture = (List<Fornitura>) request.getAttribute("preferitiFornitura");%>
<%List<Colore> colori = (List<Colore>) request.getAttribute("preferitiColore");%>

<div id="modifica">
<%if(merci==null){%>
Non ci sono ancora preferiti
<%}else{%>
<%System.out.println("queeeeetr");%>
<table>
    <caption>Lista dei preferiti</caption>
    <thead>
    <tr>
        <th>Immagine</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Colore</th>
        <th>Taglia</th>
        <th>Prezzo</th>
    </tr>
    </thead>
    <tbody>
    <%System.out.println("unooooooos");%>
    <%
        for(int i = 0;i<merci.size();i++){
    System.out.println("DUUUUESSSS");
            int fcodice=0;
            String c="";
    %>
    <%System.out.println("12121212");%>
    <tr id="<%=i%>">
        <td data-head="Immagine"><img src="../Images/<%=merci.get(i).getCodice()%>.jpg" alt="img" width="700" height="500"></td>
        <td data-head="Nome"><%=merci.get(i).getNome()%></td>
        <td data-head="Categoria"><%=merci.get(i).getTipocategoria()%></td>
        <%System.out.println("353553");%>
        <%for(Fornitura f : forniture){%>
        <%System.out.println("9999");%>
        <%if(f.getCodMerce().equals(merci.get(i).getCodice())){
        System.out.println("88888");
            for(Colore color : colori){
        System.out.println("44444");
                if(f.getCodColore()==color.getCod()){
        System.out.println("222222");
                    c =color.getTipoColore();
                    break;
                }
            }%>
        <%break;
        }%>
        <%}%>
        <%System.out.println("686876");%>
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
        <td data-head="Prezzo"><%=merci.get(i).getPrezzo()%></td>
        <%String x = ""+merci.get(i).getCodice()+","+cs.getIdUtente()+","+fcodice+","+cs.getRegistrato();%>
        <%System.out.println("boiade");%>
        <td><button name="aggiungi" id="aggiungi"  onclick="aggiungi('<%=x%>')">Aggiungi al carrello</button></td>
        <td><button name="rimuovi" id="remove"  onclick="rimuovi('<%=x%>','<%=i%>')">Rimuovi dai preferiti</button></td>
    </tr>
    <%}%>
    </tbody>
</table>
<%}%>
</div>