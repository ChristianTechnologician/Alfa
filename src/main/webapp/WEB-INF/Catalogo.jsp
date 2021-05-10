<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 10/05/2021
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>ALFA</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
  * {
    box-sizing: border-box;
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
    padding: 14px 16px;
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
    width: 100%;
    padding: 200px;
  }
  .footer {
    background-color: #f1f1f1;
    padding: 10px;
    text-align: center;
  }

  .dropbtn {
    background-color: #4CAF50;
    color: white;
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
    position: absolute;
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

  .dropdown-content a:hover {background-color: #f1f1f1}

  .dropdown:hover .dropdown-content {
    display: block;
  }

  .dropdown:hover .dropbtn {
    background-color: #3e8e41;
  }

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

<div class="header">
  <a href="HomePage.html">
    <img src="Images/logo.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">
  </a>
</div>

<ul>
  <li><div class="dropdown">
    <button class="dropbtn">Uomo</button>
    <div class="dropdown-content">
      <a href="#">Link 1</a>
      <a href="#">Link 2</a>
      <a href="#">Link 3</a>
    </div>
  </div></li>
  <li><a href="#Donna">Donna</a></li>
  <li style="float:right"><a href="#Utente">Utente</a></li>
  <li style="float:right"><a href="#Carrello">Carrello</a></li>
  <li style="float:right"><a href="#Preferiti">Preferiti</a></li>
  <li style="float:right">
    <form id="search_form" action="" method="post">
      <input id="search_input" size="20"name="keyword" placeholder="Barra di ricerca"/>
      <button><i  style="font-size:30px" class="fa">&#xf002;</i></button>
    </form></li>
</ul>



<div class="row">
  <div class="column">
    <ul>
      <li style="float: left">abito</li>
      <li style="float: left">giacca</li>
    </ul>


  </div>
</div>

<div class="footer">
  <p>
    Info sull’azienda:
    Alfa, Via Inventina 290(PT), P.I  IT 11257691002, Alfa S.r.l© Copyright 2021 Alfa.
    Tutti i diritti riservati;



    Info sull’autore:
    Capone,  Caruso, Coticelli;

    informazioni d’obbligo:
    “Alfa” ed il logo “Alfa” sono trademark di Alfa S.r.l e rappresenta marchi registrati globalmente.
    Policy del sito termini e condizioni.

    Chi siamo:
    άλφα è un sito emergente che punta a diventare uno dei più importanti e-commerce di abbigliamento di classe proponendo abiti e giacche con lo scopo di coinvolgere utenti di qualsiasi fascia d’età e di interesse globale.
  </p>
</div>
</body>
</html>
