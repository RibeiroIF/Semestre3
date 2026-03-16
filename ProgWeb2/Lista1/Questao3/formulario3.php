<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="icon" type="image/x-icon" href="https://rwnobrega.page/_assets/ifsc-logo.png">
   <link rel="stylesheet" href="infoHardware.css">
   <title> Questão introdutória 3 POO - PRW2 </title>
</head>
<body>
   <header>
      <h1> Questão introdutória 3 POO - PRW2 </h1>
   </header>

   <div class="corpo">
      <div class="enunciado">
         <h2 style="text-align: center;"> Questão Introdutória 3 </h2>
         <p> Criar um formulário que permita ao usuário inserir as informações de um item de informática, entre elas estão: </p>
         <ul>
            <li> Nome do item </li>
            <li> Classificação (hardware ou software) com botões de rádio </li>
            <li> Preço de venda </li>
            <li> Método construtor da classe </li>
            <li> Método para calcular o desconto dado pela loja.
               <ul>
                  <li> <span> Hardware </span> = 5% </li>
                  <li> <span> Software </span> = 7% </li>
               </ul> </li>
            <li> Método para exibir o preço final do item após o desconto </li>
         </ul>
      </div>
      <div class="formulario">
         <div class="formulario-texto">
            <form action="formulario3.php" method="post" id="formulario3">
               <fieldset>
                  <legend> Espaço para infos do item </legend>
                  <label class="alinha"> Nome do item: </label>
                  <input type="text" name="nome"> <br>
                  <label class="alinha"> Classificação: </label> 
                  <input type="radio" name="classificacao" id="hardware" checked>
                  <label for="hardware"> <span> Hardware </span> </label>
                  <input type="radio" name="classificacao" id="software">
                  <label for="software"> <span> Software </span> </label> <br>
                  <label class="alinha"> Preço: </label>
                  <input type="number" name="precoVenda" min="0" max="10000" step="0.1">
               </fieldset>
               <button form="formulario3" type="submit" class="button" name="formulario3"> Receber infos </button>
            </form>
         </div>
         <div class="formulario-resposta">
            <h2> Resultado do formulário </h2>
            <?php require 'infoHardware.php';
            
            $hardware = new Hardware();
            $hardware->calcularDesconto();
            $hardware->mostrarInfos();

            ?>
         </div>
      </div>
   </div>

   <footer>
      <h3> Aluno: Gabriel Ribeiro de Souza </h3>
   </footer>
</body>
</html>