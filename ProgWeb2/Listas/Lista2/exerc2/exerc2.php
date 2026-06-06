<!DOCTYPE html>
<html lang="pt-BR">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title> Fundamentos de Banco de Dados com PHP </title>
 <link rel="stylesheet" href="formata-banco.css">
</head>

<body>
 <h1> PHP + MySQL - exercício 2 </h1>

 <form action="exerc1.php" method="post">
  <fieldset>
   <legend> Registro do produto </legend>
    
   <label> ID do produto: </label> <br>
   <input type="text" name="id" autofocus> <br> <br>

   <label> Preço Unitário: </label> <br>
   <input type="number" name="preco" min="0" step="0.01"> <br> <br>

   <label> Estoque: </label> <br>
   <input type="number" name="estoque" min="0"> <br> <br>

   <label> Classificação do produto: </label> <br>
   <input type="radio" name="classific" value="Perecível"> <label> Produto perecível </label> <br>
   <input type="radio" name="classific" value="Não-Perecível"> <label> Produto não-perecível </label> <br>

   <label> Descrição detalhada do produto: </label>
   <textarea name="descricao" maxlength="500"></textarea>
   <div>
    <label> Escolha uma das operações a seguir </label>
    <select name="operacoes">
        <option> Cadastrar produto no banco de dados </option>
        <option> Tabular dados dos produtos perecíveis </option>
        <option> Mostrar descrição do produto com menor quantidade </option>
        <option> Calcular o faturamento da venda de todos os não-perecíveis </option>
    </select> <br>
    <button name="enviar" class="button"> Executar ação </button>
   </div>
  </fieldset>
 </form> 
 <?php

  require "criar-classe-banco-de-dados.inc.php";
  require "criar-classe-produtos.inc.php";

  $objBanco = new BancoDeDados("localhost", "root", "", "CTDS", "produtos");

  $conexao = $objBanco->criarConexao();
  $objBanco->criarBanco($conexao);
  $objBanco->abrirBanco($conexao);
  $objBanco->definirCharset($conexao);
  $objBanco->criarTabela($conexao); 
  $objProduto = new Produtos();  

  if(isset($_POST["cadastrar"]))
   {
   $objProduto->receberDadosForm($conexao);
   $objProduto->cadastrar($conexao, $objBanco->nomeDaTabela);
   echo "<p> Dados do aluno cadastrados com sucesso na base de dados. </p>";
   }

  if(isset($_POST["tabular"]))
   {
   $objProduto->tabularDados($conexao, $objBanco->nomeDaTabela);
   }

  if(isset($_POST["contar"]))
   {
   $objProduto->contarAprovados($conexao, $objBanco->nomeDaTabela);
   } 

  $objBanco->desconectar($conexao);
 ?>
</body>
</html>