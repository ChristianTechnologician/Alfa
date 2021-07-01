<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String s=(String)request.getAttribute("Codice");%>
<section class="field">
    <form action="${pageContext.request.contextPath}/fornitura/inserisci" method="post">
        <input type="hidden" name="Codice" value="<%=s%>">
        <label for="Colore">
            <input type="text" name="Colore" id="Colore" placeholder="COLORE">
        </label>
        <label>
            <select name="Taglia">
                <option value='S'>S</option>
                <option value='M'>M</option>
                <option value='L'>L</option>
                <option value='XL'>XL</option>
                <option value='XXL'>XXL</option>
            </select>
        </label>
        <label for="Quantita">
            <input type="text" name="Quantita" id="Quantita" placeholder="QUANTITA'">
        </label>
        <button>Inserisci</button>
    </form>
</section>