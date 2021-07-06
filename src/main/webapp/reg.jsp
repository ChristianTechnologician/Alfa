<form action="${pageContext.request.contextPath}/utente/create" method="post" onsubmit="event.preventDefault(); validateForm(this)">
    <fieldset>
        <h2>Registrazione</h2>
        <span>Nome</span>
        <label for="name">
            <input type="text" name="name" id="name" placeholder="Nome">
        </label>
        <span>Cognome</span>
        <label for="cognome">
            <input type="text" name="cognome" id="cognome" placeholder="Cognome">
        </label>
        <span>Email</span>
        <label for="email">
            <input type="email" name="email" id="email" placeholder="Email">
        </label>
        <span>Password</span>
        <label for="password">
            <input type="password" name="password" id="password" placeholder="Password">
        </label>
        <button>Registrati</button>
    </fieldset>
</form>