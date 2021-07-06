<script src="https://kit.fontawesome.com/yourcode.js" crossorigin="anonymous"></script>
<div class="header">
    <a href="HomePage.html">
        <img src="${pageContext.request.contextPath}/Images/LOGO.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">
    </a>
</div>

<ul>
    <div class="dropdown"> <form action="RedirectServlet" method="get">
        <li><button class="dropbtn" id="r_uomo" name="redirect_uomo">Uomo</button>
            <div class="dropdown-content">
                <button class="key" id="redirect_uomo_abiti" name="redirect_uomo_abiti">Abiti</button>
                <button class="key" id="redirect_uomo_giacche" name="redirect_uomo_giacche">Giacche</button>
            </div></li>
    </form>
    </div>

    <div class="dropdown"><form action="RedirectServlet" method="get">
        <li><button class="dropbtn" id="r_donna" name="redirect_donna">Donna</button>
            <div class="dropdown-content">
                <button class="key" id="redirect_donna_abiti" name="redirect_donna_abiti">Abiti</button>
                <button class="key" id="redirect_donna_giacche" name="redirect_donna_giacche">Giacche</button>
            </div></li></form>
    </div>

    <div class="dropdown" style="float: right"><form action="./utente/user" method="get">
        <li><button class="dropbtn" style="font-size:24px"><i class="fas fa-user-alt"></i></button></li></form>
    </div>
    <li style="float:right"><a href="#Carrello">Carrello</a></li>
    <li style="float:right"><a href="#Preferiti">Preferiti</a></li>
    <li style="float:right">

        <form action="research-servlet" method="get">
            <input id="search_input" size="20"name="keyword" placeholder="Barra di ricerca"/>
            <button style="border: none;cursor: pointer"><i  style="font-size:25px;padding-top: 10px" class="fa">&#xf002;</i></button>
        </form></li>
</ul>
