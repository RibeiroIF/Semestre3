function validarForm(evento)
 {
 let login = document.getElementById("login").value;
 let senha = document.getElementById("senha").value;

 let objErroLogin = document.getElementById("erro-login");
 let objErroSenha = document.getElementById("erro-senha");

 let temErro = false;

 login = login.trim();
 senha = senha.trim();

 if(login.length == 0)
  {
  temErro = true;
  objErroLogin.innerHTML = "<span> ERRO: login inválido! </span>";
  }
 else
  {
  objErroLogin.innerHTML = "";
  }

 if(senha.length < 10)
  {
  temErro = true;
  objErroSenha.innerHTML = "<span> ERRO: senha inválida! </span>";
  }
 else
  {
  objErroSenha.innerHTML = "";
  }

 if(temErro)
  {
  evento.preventDefault();
  }
 }

let objForm = document.getElementById("formulario");
objForm.addEventListener("submit", validarForm);