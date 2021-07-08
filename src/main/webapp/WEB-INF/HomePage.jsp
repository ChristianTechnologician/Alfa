<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Home"/>
        <jsp:param name="style" value="reset,libraryCustomer"/>
        <jsp:param name="script" value=""/>
    </jsp:include>
</head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<header>
<%@include file="/WEB-INF/views/partials/HeaderCustomer.jsp"%>
</header>

<div class="row">
    <div class="column" style="float:left">
        <h2>Abbigliamento Maschile</h2>
        <form action="${pageContext.request.contextPath}/RedirectServlet" method="get">
            <button>
                <img src="../Images/MAN.jpg" alt="Impossibile caricare l'immagine" width="400" height="600" >
                <input type="hidden" id="redirect_uomo" name="redirect_uomo">
            </button>
        </form>
    </div>
</div>


<div class="row">
    <div class="column" style="float:right">
        <h2>Abbigliamento Femminile</h2>
        <form action="${pageContext.request.contextPath}/RedirectServlet" method="get">
            <button>
                <img src="../Images/WOMAN.jpg" alt="Impossibile caricare l'immagine" width="400" height="600">
                <input type="hidden" id="redirect_donna" name="redirect_donna">
            </button>
        </form>
    </div>
</div>

<footer>
<%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</footer>

</body>
</html>