let form = document.getElementById("form1")
let button = document.getElementById("button")

form.addEventListener("submit", e=>{
    e.preventDefault()

    let peso = Number(document.getElementById("peso").value)
    let altura = Number(document.getElementById("altura").value)

    let imc = peso / (altura*altura)
    let resultado = ""

    if(imc < 17){
        resultado = "Abaixo do peso"
    }
    else if(imc < 25){
        resultado = "Normal"
    }
    else if(imc < 30){
        resultado = "Sobrepeso"
    }
    else {
        resultado = "Obesidade"
    }


    document.getElementById("output").innerText = `IMC: ${imc} - ${resultado}`
    console.log(imc)

})