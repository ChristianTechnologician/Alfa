
<form action="${pageContext.request.contextPath}/utente/signinCliente" method="post" onsubmit="event.preventDefault(); validateForm(this)">
    <fieldset>
        <h2>Registrazione</h2>
        <span>Nome</span>
        <label for="nome">
            <input type="text" name="password" id="nome" placeholder="Nome">
        </label>
        <span>Cognome</span>
        <label for="cognome">
            <input type="text" name="password" id="cognome" placeholder="Cognome">
        </label>
        <span>Email</span>
        <label for="email">
            <input type="email" name="email" id="email" placeholder="Email">
        </label>
        <span>Password</span>
        <label for="password">
            <input type="password" name="password" id="password" placeholder="Password">
        </label>
        <button type="submit">Registrati</button>
    </fieldset>
</form>
