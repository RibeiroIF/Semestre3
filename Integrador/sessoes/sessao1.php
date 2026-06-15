<?php
//abrindo uma sessão no código PHP
session_start();
?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title> Sessões com PHP </title>
</head>

<body>
 <h1> Gravando informações com sessões no servidor - página 1 </h1>

 <form action="sessao1.php" method="post">
  <filedset>
   <legend> Dados cadastrais </legend>

   <label> Aluno: </label>
   <input type="text" name="aluno"> <br> <br>

   <label> Média de PRWII: </label>
   <input type="number" name="media">  
  </fieldset>

  <button name="enviar"> Criar sessão </button>
 </form> <br> <br>

 <?php
  if(isset($_POST["enviar"]))
   {
   //criando o vetor de sessão
   $_SESSION["aluno"] = $_POST["aluno"];
   $_SESSION["media"] = $_POST["media"];
   $_SESSION["data"]  = date("d/m/Y");
   }
 ?> 

 <a href="sessao2.php"> Ir para a próxima página e recuperar ou apagar a sessão </a>
</body>
</html>