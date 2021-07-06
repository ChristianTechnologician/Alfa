<!DOCTYPE html>
<html lang="it">



<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a71707a89a.js" crossorigin="anonymous"></script>

    <title>Popup Box</title>

    <style>
        body{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .btn{
            position: absolute;
            top: 50%;
            left: 50%;
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
<div id="popUpOverlay"></div>
<div id="popUpBox">
    <div id="box">
        <i class="fas fa-check-circle fa-5x"></i>
        <h1>Here Goes Your Popup</h1>
        <div id="closeModal"></div>
    </div>
</div>

<button onclick="Alert.render('You look very pretty today.')" class="btn">Show Alert</button>
<script src="javascript/app.js"></script>
</body>
</html>