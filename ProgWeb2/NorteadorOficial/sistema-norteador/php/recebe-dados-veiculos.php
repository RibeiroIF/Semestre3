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
    require "../includes/criar-classe-veiculos.inc.php";

    $objBanco = new BancoDeDados("localhost", "root", "", "Sistema_lavacao", "clientes", "veiculos", "administrador");
    $conexao = $objBanco->criarConexao();
    $objBanco->criarBanco($conexao);
    $objBanco->abrirBanco($conexao);
    $objBanco->definirCharset($conexao);
    $objBanco->criarTabelaClientes($conexao);
    $objBanco->criarTabelaVeiculos($conexao);

    $objVeiculo = new Veiculos();
    $objVeiculo->receberDadosForm($conexao);
    $objVeiculo->cadastrar($conexao, $objBanco->nomeDaTabelaVeiculos);
    $objBanco->terminarConexao($conexao);
   ?>

   <p> Veículo cadastrado com sucesso! </p>

   <form action="../html/cadastro-veiculo.html" method="post">
    <button> Cadastrar outro veículo </button>
   </form>
  </main>
</body> 
</html> 