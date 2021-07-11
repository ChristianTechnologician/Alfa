<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div class="header">
    <a href="${pageContext.request.contextPath}/utente/homePage">
        <img src="../Images/LOGO.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">
    </a>
</div>

<ul>
    <div class="dropdown"> <form action="${pageContext.request.contextPath}/RedirectServlet" method="get">
        <li><button class="dropbtn" id="r_uomo" name="redirect_uomo">Uomo</button>
            <div class="dropdown-content">
                <button class="key" id="redirect_uomo_abiti" name="redirect_uomo_abiti">Abiti</button>
                <button class="key" id="redirect_uomo_giacche" name="redirect_uomo_giacche">Giacche</button>
            </div></li>
    </form>
    </div>

    <div class="dropdown"><form action="${pageContext.request.contextPath}/RedirectServlet" method="get">
        <li><button class="dropbtn" id="r_donna" name="redirect_donna">Donna</button>
            <div class="dropdown-content">
                <button class="key" id="redirect_donna_abiti" name="redirect_donna_abiti">Abiti</button>
                <button class="key" id="redirect_donna_giacche" name="redirect_donna_giacche">Giacche</button>
            </div></li></form>
    </div>

    <div class="dropdown" style="float: right"><form action="${pageContext.request.contextPath}/utente/user" method="get">
        <li><button class="dropbtn">Utente</button></li></form>
    </div>
    <div class="dropdown" style="float: right"><form action="${pageContext.request.contextPath}/utente/carrello" method="get">
        <li><button class="dropbtn">Carrello</button></li></form>
    </div>
    <div class="dropdown" style="float: right"><form action="${pageContext.request.contextPath}/utente/preferiti" method="get">
        <li><button class="dropbtn">Preferiti</button></li></form>
    </div>
    <li style="float:right">
</ul>
