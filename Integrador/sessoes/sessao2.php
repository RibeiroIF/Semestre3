<?php
//vamos começar abrindo a sessão criada no servidor
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
 <h1> Lendo e gravando informações de uma sessão em PHP - página 2 </h1>

 <form action="sessao2.php" method="post">
  <filedset>
   <legend> Mostrando dados da sessão </legend>
   
   <button name="mostrar-sessao"> Mostrar dados das variáveis de sessão </button>

   <button name="apagar-sessao"> Apagar variáveis de sessão </button> 
  </fieldset>
 </form> <br> <br>

 <?php
  //implementar a operação de acesso às variáveis de sessão
  if(isset($_POST["mostrar-sessao"]))
    {
    //devemos testar as variáveis de sessão antes de acessá-las
    if(isset($_SESSION['aluno']) and isset($_SESSION["media"]) and isset($_SESSION["data"]))
      {
      //as variáveis de sessão existem
      echo "<p> Dados recuperados da sessão criada na página anterior: <br>
            Aluno = ", $_SESSION['aluno'], "<br>
            Média = ", $_SESSION["media"], "<br>
            Data de registro = ", $_SESSION["data"], "</p>";
      }
    else
      {
      //uma ou mais variáveis de sessão não existem no servidor
      echo "<p> Erro no acesso às variáveis de sessão. </p>";
      }
    }

  //vamos tratar a operação de apagar os dados da sessão (logout)
  if(isset($_POST["apagar-sessao"]))
    {
    if(isset($_SESSION['aluno']) and isset($_SESSION["media"]) and isset($_SESSION["data"]))
      {
      //sessões existem. Vamos apagá-las
      $_SESSION = []; //apaga todas as células do vetor
      session_destroy(); //elimina o ID da sessão do usuário
      echo "<p> Sessão do usuário foi apagada com sucesso no servidor. </p>";
      }
    else
      {
      echo "<p> Erro no acesso às variáveis de sessão. </p>";
      }
    }
 ?> 
 <a href="sessao1.php"> Voltar ao início </a>
</body>
</html>