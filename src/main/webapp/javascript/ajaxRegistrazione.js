function ajax(){
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function(){
       if (this.readyState == 4 && this.status == 200) {
            document.getElementById("ajax").innerHTML = this.responseText;
       }
    }
    xhttp.open("GET","localhost/webapp/prova.txt",true);
    xhttp.send();
}