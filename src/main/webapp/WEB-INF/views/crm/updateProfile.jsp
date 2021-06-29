<section class="field">
    <form action="${pageContext.request.contextPath}/utente/update" method="post">
        <label id="#nome">
            <input type="text" name="nome" placeholder="Nome">
        </label>
        <label id="#cognome">
            <input type="text" name="cognome" placeholder="Cognome">
        </label>
        <label id="#email">
            <input type="email" name="email" placeholder="E-mail">
        </label>
        <label id="#password">
            <input type="text" name="PW" placeholder="Password">
        </label>
        <button>Salva</button>
    </form>
</section>