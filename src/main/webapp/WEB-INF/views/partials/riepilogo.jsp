<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%List<Merce> merci= (List<Merce>) request.getAttribute("merci");%>
<section class="field">
    <%double prezzo=0;%>
    <p>
    <%
        for (Merce m: merci) {%>
        <%prezzo=prezzo+m.getPrezzo();%>
        Nome:<%=m.getNome()%> Prezzo:<%=m.getPrezzo()%>
        <%}%></p>
    <p><h2>PREZZO TOTALE: €<%=prezzo%></h2></p>
    <p><h4>Di cui iva: €<%=prezzo=(prezzo/100)*22%></h4></p>
</section>
