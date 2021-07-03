<div>
    <form action="${pageContext.request.contextPath}/utente/signinCliente" method="post" onsubmit="event.preventDefault(); validateForm(this)">
        <fieldset>
            <h2>Registrazione</h2>
            <span>Password</span>
            <label for="password">
                <input type="password" name="password" id="password" placeholder="Password">
            </label>
            <span>Password</span>
            <label for="password">
                <input type="password" name="password" id="password" placeholder="Password">
            </label>
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