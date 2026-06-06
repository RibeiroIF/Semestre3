let telaAnterior = 'tela-home'
let telaAtual = 'tela-home'

function navegar(destino){
    let telas = document.getElementsByClassName('tela')
    Array.from(telas).forEach(element => {
        element.classList.remove('show')
        element.classList.add('collapse')
    });
    document.getElementById(destino).classList.remove('collapse')
    document.getElementById(destino).classList.add('show')
    telaAnterior = telaAtual
    telaAtual = destino
}

function voltar() {
    navegar(telaAnterior)
}