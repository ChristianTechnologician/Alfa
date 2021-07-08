<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
<style>
    body{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    .btn{
        position: absolute;
        transform: translate(-50%, -50%);
        background: pink;
        font-size: 20px;
        color: white;
        padding: 10px 30px;
        cursor: pointer;
    }

    #popUpBox{
        width: 500px;
        overflow: hidden;
        background: pink;
        box-shadow: 0 0 10px black;
        border-radius: 10px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 9999;
        padding: 10px;
        text-align: center;
        display: none;
    }
</style>
</head>
<body>
<h1><%= "salve conte " %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>

<!--<input type="hidden" onclick="Alert.render('You look very pretty today.')" class="btn">-->