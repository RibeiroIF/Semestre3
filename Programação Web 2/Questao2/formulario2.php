<!DOCTYPE html>
<html lang="pt-BR">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="icon" type="image/x-icon" href="https://rwnobrega.page/_assets/ifsc-logo.png">
   <link rel="stylesheet" href="infoCarro.css">
   <title> Questão introdutória 2 POO - PRW2 </title>
</head>
<body>
   <header>
      <h1> Questão introdutória 2 POO - PRW2 </h1>
   </header>

   <div class="corpo">
      <div class="enunciado">
         <h2 style="text-align: center;"> Questão Introdutória </h2>
         <p> Criar um formulário que permita ao usuário inserir as informações de um carro, entre elas estão: </p>
         <ul>
            <li> Fabricante </li>
            <li> Modelo </li>
            <li> Preço de Venda </li>
            <li> Método construtor da classe </li>
            <li> Método para exibir a classificação de um carro:
               <ul>
                  <li> <span> Popular: </span> Até 100 mil reais </li>
                  <li> <span> Performance intermediária: </span> 100 - 300 mil reais </li>
                  <li> <span> Alta performance: </span> Acima de 300 mil reais </li>
               </ul> </li>
            <li> Método para saber todas as informações de um carro somente popular </li>
         </ul>
      </div>
      <div class="formulario">
         <div class="formulario-texto">
            <form action="formulario2.php" method="post" id="formulario2">
               <fieldset>
                  <legend> Espaço para infos do livro </legend>
                  <label class="alinha"> Fabricante: </label>
                  <input type="text" name="fabricante"> <br>
                  <label class="alinha"> Modelo: </label>
                  <input type="text" name="modelo"> <br>
                  <label class="precoVenda"> Preço de venda: </label>
                  <input type="number" name="precoVenda" min="0" max="99999999"> <br>
               </fieldset>
               <button form="formulario2" type="submit" class="button" name="formulario2"> Receber infos </button>
            </form>
         </div>
         <div class="formulario-resposta">
            <h2> Resultado do formulário </h2>
            <?php require 'infoCarro.php';
            
            $carro = new Carro();
            $carro->classificarCarro();
            $carro->exibirInfos();

            ?>
         </div>
      </div>
   </div>

   <footer>
      <h3> Aluno: Gabriel Ribeiro de Souza </h3>
   </footer>
</body>
</html>