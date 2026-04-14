


document.querySelector(".botao").addEventListener("click", e=>{
    let numero1 = document.getElementById("numero1").value;
    let numero2 = document.getElementById("numero2").value;
    let resultado = Number(numero1) + Number(numero2);

    alert(resultado)
    document.querySelector(".resposta").textContent = resultado;
})