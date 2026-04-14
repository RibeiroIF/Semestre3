function addTarefa(event){
    let tarefa = document.getElementById("tarefa").value
    let lista = document.querySelector("ul")

    let novaTarefa = document.createElement("li")
    novaTarefa.innerText = tarefa

    let botaoDelete = document.createElement("span")
    botaoDelete.innerText = "[X]"

    botaoDelete.addEventListener("click", e=>{
        lista.removeChild(novaTarefa)
    })

   
    novaTarefa.append(botaoDelete)
    lista.appendChild(novaTarefa)
}