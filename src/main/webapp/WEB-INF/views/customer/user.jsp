<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login Utente"/>
        <jsp:param name="script" value="loginValidator,ajaxRegistrazione"/>
    </jsp:include>
</head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style type="text/css">

        * {
            box-sizing: border-box;
            font-family: 'Merryweather' ;
            font-style:normal;
            font-weight: normal;
        }

        body {
            margin: 0;
        }

        .header {
            background-color: #f1f1f1;
            padding: 8px;
            text-align: center;
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            border: 1px solid #e7e7e7;
            background-color: #f3f3f3;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: #666666;
            text-align: center;
            padding: 16px 16px;
            text-decoration: none;
        }

        li a:hover:not(.active) {
            background-color: #dddddd;
        }

        /*li a.active {
             color: white;
             background-color: #dddddd;
         }*/

        .column {
            background-color: #f1f1f1;
            float: left;
            text-align: center;
            width: 50%;
            padding: 200px;
        }

        .footer {
            width: auto;
            background-color: #f1f1f1;
            padding: 10px;
            text-align: center;
        }

        .dropbtn {
            background-color: #f1f1f1;
            color: black;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: fixed;
            overflow: no-display;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #4CAF50}

        .dropdown:hover .dropdown-content {
            display:block;
        }

        .dropdown:hover .dropbtn {
            background-color: #3e8e41;
        }

        .key {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            width:100%;
            border:none;
            cursor: pointer;
        }

        .key:hover{background-color: #4CAF50}

        #search_form {
            display:inline-block;
            font: bold 10px Verdana,Geneva,Arial,Helvetica,sans-serif;
            font-size: medium;
            color:black;
        }

        #search_form input[type="image"] {
            display:inline-block;
            height:15px;
        }


        #search_input {
            font-size:medium;
            alignment: center;
        }
    </style>

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