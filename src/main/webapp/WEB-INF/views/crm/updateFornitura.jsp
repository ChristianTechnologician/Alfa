<%@ page import="Model.Merce.Merce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-DeleteMerce"/>
        <jsp:param name="style" value="crm"/>
        <jsp:param name="script" value="crm"/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<!--
<%//String codice = (String) session.getAttribute("codice"); %>

<section class="field">
    <form action="/merce/updateFornitura" method="post">
        <label id="#colore">
            Inserisci il codice del prodotto che vuoi modificare
            <input type="text" name="colore" placeholder="Codice">
        </label>
        <label id="#taglia">
            <input type="text" name="taglia" placeholder="Nome">
        </label>
        <label id="#quantita">
            <input type="text" name="quantita" placeholder="Descrizione">
        </label>
        +
        <button>Aggiungi forniture<input type="hidden" name="Codice" value="<%=codice%>"> </button>
    </form>
</section>-->
<form name="su">
    <input type="text" name="tt"  id="sub" />
</form>
<div id="form_container">
    <form id="subscribe_frm" style="display:none">NAME:
        <input type="text" name="text" />EMAIL:
        <input type="text" name="text" />PASSWORD:
        <input type="text" name="text" />
    </form>
</div>
<script>
function toggleFormVisibility() {

var txt = document.getElementById('sub').value;
var neededChildren = txt.length - document.getElementById('form_container').children.length + 1;

for (var i = 0; i < neededChildren; i++) {
var frm_element = document.getElementById('subscribe_frm').cloneNode(true);
var vis = frm_element.style;
vis['display'] = 'block';
document.getElementById("form_container").appendChild(frm_element);
}

}
document.getElementById('sub').addEventListener('keyup', toggleFormVisibility);
</script>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>