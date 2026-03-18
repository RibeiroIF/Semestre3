document.querySelector(".botao").addEventListener("click", e=>{

    let item = document.getElementById("input").value;

    const novoItem = document.createElement("li");
    const lista = document.querySelector(".lista")
    
    novoItem.innerText = item
    lista.appendChild(novoItem);


})