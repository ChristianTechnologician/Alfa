let errors=[];

function validateForm(request, update)
{
    let validator = new RequestValidator(request);
    let bool;
    let boolbis;
    bool=assertMatch("name", new RegExp("^\\w{4,20}$"),"il nome utente deve iniziare con una lettera e  avere lunghezza compresa tra i 4 e i 20 caratteri");
    if(update)
    {
        boolbis=assertInt("id","Id deve essere un numero intero");
    }
    return validator;
}

function validateSignIn(request)
{
    let validator = new RequestValidator(request);
    validator.assertEmail("email","Inserire l'email corrispondente al tuo account");
    return validator;
}

function RequestValidator(request)
{
    let requestx = request;
    return requestx;
}

function gatherError(condition, message)
{
    if(condition)
    {
        return true;
    }
    else
    {
        errors.push(message);
        return false;
    }
}

function isBlank(value)
{
    //verifica se è vuota o ha solo spazi bianchi
    return (value.length === 0 || !value.trim());
}

function required(value)
{
    return  value != null && isBlank(value);
}

function assertMatch(value, regexp, msg)
{
    let param = value;
    condition.matcher=param;
    let condition = required(param) && regexp.matcher(param).matches();
    return  gatherError(condition,msg);
}

function assertInt(value, msg)
{
    let INT_PATTERN= new RegExp("^\\d+$");
    return assertMatch(value,INT_PATTERN,msg);
}

function assertEmail(value, msg)
{
    let pattern = new RegExp("^[a-zA-Z0-9.!#$%&´*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    return assertMatch(value,pattern,msg);
}