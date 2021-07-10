<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<div class="row">
    <div class="column" style="float:left">
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
    </div>
    <%request.setAttribute("total", prezzo);%>
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
                <label for="Città">
                    <input type="text" name="Città" id="Città" placeholder="Citta'">
                </label>
                <label for="Provincia">
                    <input type="text" name="Provincia" id="Provincia" placeholder="Provincia">
                </label>
                <label for="Carta di credito">
                    <input type="number" name="Carta di credito" id="Carta di credito" placeholder="Carta di credito">
                </label>
                <button>ACQUISTA</button>
            </form>
        </section>
    </div>
</div>