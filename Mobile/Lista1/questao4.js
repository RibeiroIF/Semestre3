let contador = 0
const numero = document.querySelector(".numero")

document.querySelector(".diminuir").addEventListener("click", e=>{
    contador--;
    numero.textContent = contador;
})
document.querySelector(".aumentar").addEventListener("click", e=>{
    contador++;
    numero.textContent = contador;
})