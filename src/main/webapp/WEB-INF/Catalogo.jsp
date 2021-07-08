<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
<jsp:include page="/WEB-INF/views/partials/head.jsp">
    <jsp:param name="title" value="Catalogo"/>
    <jsp:param name="style" value="reset,libraryCustomer"/>
    <jsp:param name="script" value=""/>
</jsp:include>
</head>
<body>

<%
    List<Merce> generale = (List<Merce>) request.getAttribute("generale");
%>
<header>
<%@include file="/WEB-INF/views/partials/HeaderCustomer.jsp"%>
</header>
<section>
<div class="row">
  <div class="column" style="float:left; width :20%">
        <form id="filter_form" action="${pageContext.request.contextPath}/merce/search" method="get">
          <ul>
             <li style="float: left">Applica un filtro</li><br><br>
              <div style="float: left">
                  <input type="radio" id="Giacca" name="tipoCategoria" value="Giacca">
                  <label for="Giacca">Giacca</label><br>
                  <input type="radio" id="Abito" name="tipoCategoria" value="Abito">
                  <label for="Abito">Abito</label><br>
              </div><br>
              <li style="float: left">Taglia</li>
              <select name="taglia" style="float: left">
                  <option>S</option>
                  <option>M</option>
                  <option>L</option>
              </select><br>
              <li style="float: left">Prezzo</li>
              <div style="float: left">
              <label for="minPrice">
                  <input type="number" name="minPrice" id="minPrice" placeholder="Prezzo minimo">
              </label>
              <label for="maxPrice">
                  <input type="number" name="maxPrice" id="maxPrice" placeholder="Prezzo massimo">
              </label>
              <label for="range">
                  <input type="range" name="maxPrice" id="range">
              </label>
              </div><br>
              <li style="float: left">Scontato</li>
              <div style="float: left">
                  <input type="checkbox" name="Sconto"  value="10%">
                  <input type="checkbox" name="Sconto"  value="20%">
                  <input type="checkbox" name="Sconto"  value="30%">
              </div>
          </ul>
           <br><br> <input style="float: left" type="submit" name="applica_filtro" value="Applica">
        </form>
  </div>
</div>

  <div class="row">
  <div class="column" style="float:right;width:80%">
    <ul>
    <% for(Merce g : generale){
        if (g != null ){
    %>
     <li style="float: left "> <div class="gallery">
         <form action="DettaglioServlet" method="get">
        <button>
          <img src="./Images/<%=g.getCodice()%>.jpg" alt="img" width="700" height="500">
            <input type="hidden" name="prodotto" value="<%=g.getCodice()%>">
        </button>
        <div class="desc"><%=g.getNome()%></div></form>
      </div></li>
        <%}%>
        <%}%>
     
    </ul>
  </div>
</div>
</section>
<footer>
<%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</footer>
</body>
</html>
