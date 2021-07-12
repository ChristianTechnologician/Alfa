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
    <%@include file="/Headermini.jsp"%>
</header>
  <div class="column" style="width:100%">
      <div style="text-align: center">
    <ul>
    <% for(Merce g : generale){
        if (g != null ){
    %>
     <li style="float: left "> <div class="gallery">
         <form action="DettaglioServlet" method="get">
        <button style="width: 400px; height: 600px; border: none">
          <img src="./Images/<%=g.getCodice()%>.jpg" alt="img" width="100%" height="auto">
            <input type="hidden" name="prodotto" value="<%=g.getCodice()%>">
            <div class="desc"><%=g.getNome()%></div>
        </button>
        </form>
      </div></li>
        <%}%>
        <%}%>
     
    </ul>
      </div>
  </div>
<footer>
<%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</footer>
</body>
</html>
