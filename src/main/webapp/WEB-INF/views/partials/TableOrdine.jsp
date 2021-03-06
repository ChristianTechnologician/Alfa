<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Carrello.CarrelloSession" %>
<div class="row">
    <div class="column" style="float:left">
        <%List<Merce> merci= (List<Merce>) request.getAttribute("merci");%>
        <%CarrelloSession ss= (CarrelloSession) request.getAttribute("interi");%>
        <section class="field">
            <%double prezzo=0;%>
            <%double iva=0;%>
            <p>
                <%int i=0;
                    for (Merce m: merci) {%>
                <%prezzo=prezzo+(m.getPrezzo() * ss.Quantita().get(i));%>
                Nome:<%=m.getNome()%> Prezzo:<%=m.getPrezzo()%>
                <%i++;}%></p>
            <p><h2>PREZZO TOTALE: $<%=prezzo%></h2></p>
            <p><h4>Di cui iva: $<%=(prezzo/100)*22%></h4></p>
        </section>
    </div>
</div>
<div class="row">
    <div class="column" style="float:right">
        <section class="field">
            <form action="${pageContext.request.contextPath}/carrello/acquista" method="post">
                <label for="Via">
                    <input type="text" name="Via" id="Via" placeholder="Via">
                </label>
                <label for="NumeroCivico">
                    <input type="text" name="NumeroCivico" id="NumeroCivico" placeholder="NumeroCivico">
                </label>
                <label for="Citta">
                    <input type="text" name="Citta" id="Citta" placeholder="Citta'">
                </label>
                <label for="Provincia">
                    <input type="text" name="Provincia" id="Provincia" placeholder="Provincia">
                </label>
                <label for="Carta di credito">
                    <input type="number" name="Carta di credito" id="Carta di credito" placeholder="Carta di credito">
                </label>
                <input type="hidden" value="<%=prezzo%>" name="total">
                <button class="premi">ACQUISTA</button>
            </form>
        </section>
    </div>
</div>