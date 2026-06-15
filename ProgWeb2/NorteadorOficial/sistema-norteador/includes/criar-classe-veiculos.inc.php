<?php
 class Veiculos {
  public $fabricante;
  public $modelo;
  public $placa;


  function receberDadosForm($conexao)
   {
   $this->fabricante   = trim($conexao->escape_string($_POST["fabricante"]));
   $this->modelo       = trim($conexao->escape_string($_POST["modelo"]));
   $this->placa        = trim($conexao->escape_string($_POST["placa"]));
   }

  function cadastrar($conexao, $nomeDaTabela)
   {
   $sql = "INSERT $nomeDaTabela VALUES(
            null,
            '$this->fabricante',
            '$this->modelo',
            '$this->placa')";

   $conexao->query($sql) or die($conexao->error);
   }
 }