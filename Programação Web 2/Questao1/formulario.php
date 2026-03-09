<!DOCTYPE html>
<html lang="pt-BR">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="icon" type="image/x-icon" href="https://rwnobrega.page/_assets/ifsc-logo.png">
   <link rel="stylesheet" href="infoLivro.css">
   <title> Questão introdutória 1 POO - PRW2 </title>
</head>
<body>
   <header>
      <h1> Questão introdutória POO - PRW2 </h1>
   </header>

   <div class="corpo">
      <div class="enunciado">
         <h2 style="text-align: center;"> Questão Introdutória </h2>
         <p> Criar um formulário que permita ao usuário inserir as informações de um livro, entre elas estão: </p>
         <ul>
            <li> Título do Livro </li>
            <li> Autor do Livro </li>
            <li> ISBN do Livro </li>
            <li> Preço de Venda do Livro </li>
            <li> Método construtor da classe </li>
            <li> Método para aplicar um certo percentual de desconto </li>
            <li> Método para exibir todas as infos de um livro </li>
         </ul>
      </div>
      <div class="formulario">
         <div class="formulario-texto">
            <form action="formulario.php" method="post" id="formulario">
               <fieldset>
                  <legend> Espaço para infos do livro </legend>
                  <label class="alinha"> Titulo: </label>
                  <input type="text" name="titulo" autofocus placeholder="Título..."> <br>
                  <label class="alinha"> Autor: </label>
                  <input type="text" name="autor"> <br>
                  <label class="alinha"> ISBN: </label>
                  <input type="number" name="isbn" min="0" max="99999999"> <br>
                  <label class="alinha"> Preço: </label>
                  <input type="number" name="precoVenda" min="0" max="10000" step="0.1"> <br>
                  <label class="alinha"> Percentual de desconto: </label>
                  <input type="number" name="percentualDesconto" min="0" max="100" step="0.1">
                  <label>%</label>
               </fieldset>
               <button form="formulario" type="submit" class="button" name="formulario"> Receber infos </button>
            </form>
         </div>
         <div class="formulario-resposta">
            <h2> Resultado do formulário </h2>
            <?php require 'infoLivro.php';
            
            $livro = new Livro();
            $livro->aplicarDesconto();
            $livro->mostrarInfos();
            
            ?>
         </div>
      </div>
   </div>

   <footer>
      <h3> Aluno: Gabriel Ribeiro de Souza </h3>
   </footer>
</body>
</html>