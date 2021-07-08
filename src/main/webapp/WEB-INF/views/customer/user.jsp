<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login Utente"/>
        <jsp:param name="style" value="reset,libraryCustomer"/>
        <jsp:param name="script" value="loginValidator,ajaxRegistrazione"/>
    </jsp:include>
</head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCustomer.jsp"%>
<button onclick="ajax()">Registrati</button>
<div id="ajax">
    <form action="${pageContext.request.contextPath}/utente/signinCliente" method="post" onsubmit="event.preventDefault(); validateForm(this)">
        <fieldset>
            <h2>Login Pannello Customer</h2>
            <span>Email</span>
            <label for="email">
                <input type="email" name="email" id="email" placeholder="Email">
            </label>
            <span>Password</span>
            <label for="password">
                <input type="password" name="password" id="password" placeholder="Password">
            </label>
            <button type="submit">Accedi</button>
        </fieldset>
    </form>
</div>
<%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</body>
</html>