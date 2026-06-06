const mostrarCadastro = document.getElementById('cadastro');
const mostrarLogin = document.getElementById('login');

const formLogin = document.getElementById('form-login');
const formCadastro = document.getElementById('form-cadastro');

const opcoesLogin = document.querySelectorAll('opcoes');

document.querySelectorAll('input[name="sistema"]').forEach((radio) => {
  radio.addEventListener('change', (event) => {
    document.querySelectorAll('.form').forEach((form) => {
      form.style.display = "none";
    });

    let targetFormId = event.target.value;

    document.getElementById(targetFormId).style.display = "block";
  })
})

document.querySelectorAll('input[name="sistema"]').addEventListener('change', handleChange)
function handleChange(e){
  let selectedValue = e.target.value;
  if(selectedValue === 'login'){
    formLogin.style.display = "block";
    formCadastro.style.display = "none";
  }
  if(selectedValue === 'cadastro'){
    formLogin.style.display = "none";
    formCadastro.style.display = "block";
  }
}

