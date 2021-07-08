<%@ page import="Model.Merce.Merce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Dettaglio"/>
        <jsp:param name="style" value="reset,libraryCustomer"/>
        <jsp:param name="script" value=""/>
    </jsp:include>
</head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<%
    Merce prodotto = (Merce) request.getAttribute("prodotto");
%>
<header>
<%@include file="/WEB-INF/views/partials/HeaderCustomer.jsp"%>
</header>
<section>
<div class="row">
    <div class="column" style="float:left;width:100%">
        <ul>
            <li style="float: left "> <div class="gallery">
                        <img src="./Images/<%=prodotto.getCodice()%>.jpg" alt="img" width="700" height="500">
                    <div class="desc"><%=prodotto.getNome()%></div>
            </div></li>
        </ul>
    </div>
</div>
</section>
<footer>
 <%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</footer>
</body>
</html>
