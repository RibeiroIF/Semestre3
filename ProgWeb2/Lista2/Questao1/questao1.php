<!DOCTYPE html>
<html lang="pt-BR">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="icon" type="image/x-icon" href="https://rwnobrega.page/_assets/ifsc-logo.png">
   <link rel="stylesheet" href="questao1.css">
   <title> Questão 1 PHP com SQL </title>
</head>
<body>
   <header>
      <h1> Questão 1 PHP com SQL </h1>
   </header>

   <div class="corpo">
      <div class="enunciado">
         <h2 style="text-align: center;"> Questão 1 </h2>
         <p> Criar um formulário que permita ao usuário inserir as informações de um aluno, entre elas estão: </p>
         <ul>
            <li> Matrícula do aluno </li>
            <li> Nome do aluno </li>
            <li> Média final em PRW </li>
         </ul>
      </div>
      <div class="formulario">
         <div class="formulario-texto">
            <form action="formulario.php" method="post" id="formulario1">
               <fieldset>
                  <legend> Espaço para infos do livro </legend>
                  <label class="alinha"> Matrícula: </label>
                  <input type="text" name="matricula" autofocus placeholder="Matrícula..."> <br>
                  <label class="alinha"> Nome: </label>
                  <input type="text" name="nome"> <br>
                  <label class="alinha"> Média final: </label>
                  <input type="number" name="mediafinal" min="0" max="10" step="0.1"> <br>
               </fieldset>
               <button form="formulario1" type="submit" class="button" name="cadastro"> Cadastrar aluno </button>
               <button form="formulario1" type="submit" class="button" name="amostra"> Mostrar alunos </button>
               <button form="formulario1" type="submit" class="button" name="contagem"> Mostrar alunos </button>
            </form>
         </div>
         <div class="formulario-resposta">
            <h2 style="text-align: center"> Resultado do formulário </h2>
            <?php 
            require "criarBDD.php";
            //require "criarAlunos.php";

            $objBanco = new BancoDeDados("localhost", "root", "", "CTDS", "alunos");
            var_dump($objBanco);

            if(isset($_POST["cadastro"])){

            }
            else if(isset($_POST["amostra"])){

            }
            else if(isset($_POST["contagem"])){

            }
            
            ?>
         </div>
      </div>
   </div>

   <footer>
      <h3> Aluno: Gabriel Ribeiro de Souza </h3>
   </footer>
</body>
</html>