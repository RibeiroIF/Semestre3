<!DOCTYPE html>
<html lang="pt-BR">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title> Fundamentos de Banco de Dados com PHP </title>
 <link rel="stylesheet" href="formata-banco.css">
</head>

<body>
 <h1> PHP + MySQL - exercício 4 </h1>

 <form action="exerc4.php" method="post">
  <fieldset>
   <legend> Módulo de cadastro dos médicos </legend>

   <label> Nome: </label> <br>
   <input type="text" name="nome-medico" autofocus> <br> <br>

   <label> CRM: </label> <br>
   <input type="text" name="crm"> 
  </fieldset>

  <fieldset>
   <legend> Módulo de cadastro dos pacientes </legend>

   <label> Nome: </label> <br>
   <input type="text" name="nome-paciente"> <br> <br>

   <label> CRM do médico responsável: </label> <br>
   <input type="text" name="crm-atendimento"> <br>

   <label> Data da internação: </label> <br>
   <input type="date" name="data"> <br>
  </fieldset>

  <fieldset>
   <legend> Módulo de pesquisa por médico </legend>

   <label> Nome do médico pesquisado: </label> <br>
   <input type="text" name="nome-pesquisa-medico"> 
  </fieldset>

  <fieldset>
   <legend> Módulo de pesquisa por data - relatórios </legend>

   <label> Forneça uma data para pesquisa: </label> <br>
   <input type="date" name="pesquisa-data"> 
  </fieldset>

  <div>
   <label> Selecione uma operação: </label> <br>
   <input type="radio" name="operacao" value="1"> <label> Cadastro de médico </label> <br>

   <input type="radio" name="operacao" value="2"> <label> Cadastro de paciente </label> <br>

   <input type="radio" name="operacao" value="3"> <label> Pesquisar médico e contar pacientes atendidos por ele </label> <br>

   <input type="radio" name="operacao" value="4"> <label> Exibir relatório por data de internação </label> <br>

   <button name="executar-operacao"> Executar operação selecionada </button>
  </div>
 </form> 

 <?php
  require "criar-classe-banco-de-dados.inc.php";
  require "criar-classe-medicos.inc.php";  
  require "criar-classe-pacientes.inc.php";  

  $objBanco = new BancoDeDados("localhost", "root", "", "CTDS", "medicos", "pacientes");

  $conexao = $objBanco->criarConexao();

  $objBanco->criarBanco($conexao);

  $objBanco->abrirBanco($conexao);

  $objBanco->definirCharset($conexao);

  $objBanco->criarTabela1($conexao); 

  $objBanco->criarTabela2($conexao); 

  //vamos criar dois objetos representando as entidades médico e paciente
  $objMedico = new Medicos();
  $objPaciente = new Pacientes();  

  if(isset($_POST["executar-operacao"]))
   {
   $operacao = $_POST["operacao"];

   if($operacao == "1")
    {
    $objMedico->receberDadosForm($conexao);
    $objMedico->cadastrar($conexao, $objBanco->nomeDaTabela1);
    echo "<p> Médico cadastrado com sucesso na base de dados. </p>";
    }

   if($operacao == "2")
    {
    $objPaciente->receberDadosForm($conexao);
    $objPaciente->cadastrar($conexao, $objBanco->nomeDaTabela2);
    echo "<p> Paciente cadastrado com sucesso na base de dados. </p>";
    }

   if($operacao == "3")
    {
    $objMedico->contarPacientesAtendidos($conexao, $objBanco->nomeDaTabela1, $objBanco->nomeDaTabela2);
    }

   if($operacao == "4")
    {
    $objPaciente->exibirRelatorio($conexao, $objBanco->nomeDaTabela2);
    }
   }  
   $objBanco->desconectar($conexao); 
 ?>
</body>
</html>