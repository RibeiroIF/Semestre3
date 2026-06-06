let paragrafo = document.querySelector(".exemplo");
let botaoMostrar = document.querySelector(".mostrar")
let botaoOcultar = document.querySelector(".ocultar")

function mostrarParagrafo(){
    paragrafo.style.display = "block";
}

function ocultarParagrafo(){
    paragrafo.style.display = "none";
}

botaoMostrar.addEventListener("click", mostrarParagrafo);
botaoOcultar.addEventListener("click", ocultarParagrafo);