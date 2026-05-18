<!DOCTYPE html> 
<html lang="pt-BR"> 
<head> 
  <meta charset="utf-8"> 
  <title> Sistemas Norteador - protótipo de aplicação web para lavação de automóveis  </title> 
  <link rel="stylesheet" href="../css/formata-lavacao.css">
</head> 

<body> 
 <div class="conteiner">

  <header>
   <h1> Desenvolvimento Web - CTDS - Sistema Norteador </h1>
  </header>  
 
  <main>
   <?php
    require "../includes/criar-classe-conexao.inc.php";
    require "../includes/criar-classe-clientes.inc.php";
   ?>

   <p> Cliente cadastrado com sucesso! </p>

   <form action="../html/cadastro-cliente.html" method="post">
    <button> Cadastrar outro cliente </button>
   </form>
  </main>
</body> 
</html> 