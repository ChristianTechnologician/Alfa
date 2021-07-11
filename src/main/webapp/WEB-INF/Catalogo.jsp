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
