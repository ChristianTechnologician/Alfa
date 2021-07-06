<%@ page import="Model.Ordine.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it" xmlns="http://www.w3.org/1999/html">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Alfa-Profilo"/>
        <jsp:param name="style" value="reset,libraryCustomer"/>
        <jsp:param name="script" value="ajaxAggiornamentoProfilo,update"/>
    </jsp:include>
</head>
<script src="https://kit.fontawesome.com/a71707a89a.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>
<%List<Ordine> ordini = (List<Ordine>)request.getAttribute("ordine");%>
<%@include file="/WEB-INF/views/partials/HeaderCustomer.jsp"%>
    <aside id="sidebar">
        <section id="widget_1" ><button onclick="ordini()">Ordini effettuati</button></section>
        <section id="widget_2" ><button onclick="modifica()">Modifica</button></section>
        <section id="widget_3" ><button onclick="elimina()">Elimina profilo</button></section>
        <!--<section><a href="${pageContext.request.contextPath}/Question.jsp"><button>elimina</button></a></section>-->
        <section id="widget_4"><a href="${pageContext.request.contextPath}/utente/logout"> Logout</a></section>
    </aside>
    <section id="central">
        <h3>Ultimo acquisto</h3>
        <div>
                <%if(ordini==null){%>
                Non ci sono ancora ordini
                <%}else{
                    int i = ordini.size()-1;
                %>
                <div>
                    <img src="./Images/<%=ordini.get(i).getCodiceMerceAcquistata()%>.jpg" alt="nessuna immagine disponibile">
                </div>
                Prezzo totale:<%=ordini.get(i).getPrezzoTotale()%> Stato consegna:<%if(ordini.get(i).getStato()==1){%>Consegna effettuata<%}else{%>Consegna ancora non effettuata<%}%>
                <%}%>
        </div>
    </section>

<div id="ordini" style="display: none"><%@include file="/ordiniUtente.jsp"%></div>


<%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</body>