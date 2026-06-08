function trocarFormRadio() {
  const formCadastro = document.getElementById("form-cadastro");
  const formLogin = document.getElementById("form-login");
  const selectedValue = document.querySelector('input[name="sistema"]:checked').value;

  if (selectedValue === "login") {
    formLogin.classList.remove("hidden");
    formCadastro.classList.add("hidden");
  } else if (selectedValue === "cadastro") {
    formLogin.classList.add("hidden");
    formCadastro.classList.remove("hidden");
  }
}

