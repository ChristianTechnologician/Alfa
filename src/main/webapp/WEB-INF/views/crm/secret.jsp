<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lanh="it" dir="ltr">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login Admin"/>
        <jsp:param name="script" value="crm,loginValidator"/>
    </jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>



<form action="${pageContext.request.contextPath}/utente/signinAdmin" method="post" onsubmit="event.preventDefault(); validateForm(this)">
    <fieldset>
        <h2>Login Pannello Admin</h2>
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
</body>
</html>
