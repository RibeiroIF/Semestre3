<?php
 class Medicos {
  public $crm;
  public $nome;
 
  function receberDadosForm($conexao)
   {
   $this->crm        = trim($conexao->escape_string($_POST["crm"]));
   $this->nome       = trim($conexao->escape_string($_POST["nome-medico"]));
   }

  function cadastrar($conexao, $nomeDaTabela1)
   {
   $sql = "INSERT $nomeDaTabela1 VALUES(
            '$this->crm',
            '$this->nome')";

   $conexao->query($sql) or die($conexao->error);
   }
  
  //método que recebe o nome do médico a ser pesquisado e verifica, no banco de dados, quantos pacientes são atendidos por este médico
  function contarPacientesAtendidos($conexao, $nomeTabela1, $nomeTabela2)
   {
   $medicoPesquisado = trim($conexao->escape_string($_POST["nome-pesquisa-medico"]));

   //a seguir, criamos a parte da consulta SQL que busca o CRM do médico pesquisado na tabela medicos
   $sql = "SELECT crm FROM $nomeTabela1 WHERE nome_medico = '$medicoPesquisado'";
   $resultado = $conexao->query($sql) or die($conexao->error);

   //testar se a consulta encontrou o médico pesquisado e retornou seu crm
   if($conexao->affected_rows == 0)
    {
    die("<p> O nome do médico pesquisado <span> $medicoPesquisado </span> não foi encontrado na base de dados. Tente novamente! </p>");
    }

   else
    {
    //passando por aqui, o MySQL encontrou e devolveu ao PHP o CRM do médico pesquisado. Vamos recuperar este CRM
    $vetorRegistro = $resultado->fetch_array();
    $crmEncontrado  = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");

    //por fim, com o crm do médico pesquisado, vamos até a tabela de pacientes, no banco de dados, e contamos quantos pacientes estão sendo atendidos por este CRM
    $sql = "SELECT COUNT(*) FROM $nomeTabela2 WHERE crm_medico = '$crmEncontrado'";

    $resultado = $conexao->query($sql) or die($conexao->error);

    $vetorRegistro = $resultado->fetch_array();
    $quantos = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");

    echo "<p> Neste momento, há um total de <span> $quantos </span> paciente(s) atendido(s) pelo médico <span> $medicoPesquisado </span>";
    }
   }
 }