<!DOCTYPE html>
<html lang="pt-BR">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title> Fundamentos de Banco de Dados com PHP </title>
 <link rel="stylesheet" href="formata-banco.css">
</head>

<body>
 <h1> PHP + MySQL - exercício 1 </h1>

 <form action="exerc1.php" method="post">
  <fieldset>
   <legend> Dados cadastrais do aluno </legend>

   <label> Aluno: </label> <br>
   <input type="text" name="aluno" autofocus> <br> <br>

   <label> Matrícula: </label> <br>
   <input type="text" name="matric"> <br> <br>

   <label> Média final de PRWII: </label> <br>
   <input type="number" name="media" min="0" max="10" step="0.1"> <br> <br>

   <div>
    <button name="cadastrar"> Cadastrar aluno </button>
    <button name="tabular"> Mostrar dados dos alunos cadastrados </button>
    <button name="contar"> Mostrar número de alunos aprovados </button>
   </div>
  </fieldset>
 </form> 
 <?php
  //vamos incluir os arquivos contendo as includes com a definição de classes
  require "criar-classe-banco-de-dados.inc.php";
  require "criar-classe-alunos.inc.php";

  //vamos usar o método construtor para criar um objeto banco
  $objBanco = new BancoDeDados("localhost", "root", "", "CTDS", "alunos");
  //visualizando o conteúdo de $objBanco

  //var_dump($objBanco);

  $conexao = $objBanco->criarConexao();

  //criar o banco de dados CTDS
  $objBanco->criarBanco($conexao);

  //abrindo o banco de dados criado no servidor
  $objBanco->abrirBanco($conexao);

  //definindo a tabela UTF8 como tabela de símbolos-padrão do MySQL
  $objBanco->definirCharset($conexao);

  //criando a tabela na base de dados
  $objBanco->criarTabela($conexao); 

  //vamos criar o objeto aluno, a partir do construtor padrão da classe Alunos
  $objAluno = new Alunos();  

  //vamos fazer o PHP descobrir qual botão do formulário foi clicado
  if(isset($_POST["cadastrar"]))
   {
   $objAluno->receberDadosForm($conexao);
   $objAluno->cadastrar($conexao, $objBanco->nomeDaTabela);
   echo "<p> Dados do aluno cadastrados com sucesso na base de dados. </p>";
   }

  if(isset($_POST["tabular"]))
   {
   $objAluno->tabularDados($conexao, $objBanco->nomeDaTabela);
   }

  if(isset($_POST["contar"]))
   {
   $objAluno->contarAprovados($conexao, $objBanco->nomeDaTabela);
   } 
   
  //encerrar a conexão do PHP com o MySQL
  $objBanco->desconectar($conexao);
 ?>
</body>
</html>