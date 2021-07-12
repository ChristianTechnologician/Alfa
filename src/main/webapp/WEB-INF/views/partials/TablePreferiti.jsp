<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Fornitura.Fornitura" %>
<%@ page import="Model.Colore.Colore" %>
<%@ page import="Model.Preferiti.PreferitiSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Merce> merci = (List<Merce>) request.getAttribute("preferitiMerce");%>
<%List<Integer> quantita = (List<Integer>) request.getAttribute("preferitiQuantita");%>
<%PreferitiSession cs = (PreferitiSession) request.getSession().getAttribute("preferiti");
    List<Integer> cod = cs.Fcodice();%>
<%List<Fornitura> forniture = (List<Fornitura>) request.getAttribute("preferitiFornitura");%>
<%List<Colore> colori = (List<Colore>) request.getAttribute("preferitiColore");%>
<%System.out.println(cs);%>
<%if(merci.isEmpty()||merci.get(0).getCodice().equals("niente")){%>
Non ci sono ancora preferiti
<%}else{%>
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
    <%if (merci == null) {%>
    <tr>
    <td>Non ci sono Merci</td>
    </tr>
       <% }else{
            for (int i = 0; i < merci.size(); i++) {
                int fcodice = 0;
                int rc = 0;
                String c = "";
    %>
    <tr id="<%=i%>">
        <td data-head="Immagine"><img src="../Images/<%=merci.get(i).getCodice()%>.jpg" alt="img" width="700" height="500"></td>
        <td data-head="Nome"><%=merci.get(i).getNome()%></td>
        <td data-head="Categoria"><%=merci.get(i).getTipocategoria()%></td>
        <%for (Fornitura f : forniture) {
            if (f.getCodMerce().equals(merci.get(i).getCodice())) {
                if(f.getIdentificatore()==cs.Fcodice().get(i)) {
                    for (Colore color : colori) {
                        if (f.getCodColore() == color.getCod()) {
                            c = color.getTipoColore();
                            break;
                        }
                    }
                }
            }
        %>
        <%}%>
        <td data-head="Colore"><%=c%></td>
        <%for (Fornitura f : forniture) {%>
        <%if (f.getCodMerce().equals(merci.get(i).getCodice())) {%>
        <%
            if(f.getIdentificatore()==cs.Fcodice().get(i)) {
            //for (int codice : cod) {
               // if (f.getIdentificatore() == codice) {
        %>
        <%rc = quantita.get(i);%>
        <%fcodice = cs.Fcodice().get(i);%>
        <% c = f.getlTaglia();
            break;
        }%>
        <%}%>
        <%
            }
        %>
        <td data-head="Taglia"><%=c%></td>
        <td data-head="Prezzo"><%=merci.get(i).getPrezzo()%>
        </td>
        <%
            String x = "" + merci.get(i).getCodice() + "," + cs.getIdUtente() + "," + fcodice + "," + rc + "," + cs.getRegistrato();%>
        <td>
            <button name="aggiungi" id="aggiungi" onclick="aggiungi('<%=x%>')">Aggiungi al carrello</button>
        </td>
        <td>
            <button name="rimuovi" id="remove" onclick="rimuovi('<%=x%>','<%=i%>')">Rimuovi dai preferiti</button>
        </td>
    </tr>
    <%
            }
            }
       %>
    </tbody>
</table>
<%}%>