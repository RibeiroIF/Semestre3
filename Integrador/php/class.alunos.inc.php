<?php
 class Alunos {
  public $idUsuario;
  public $curso;
 
  function receberDadosForm($conexao)
   {
   // O idUsuario será capturado após a inserção na tabela pai (Usuarios)
   $this->curso = trim($conexao->escape_string($_POST["curso"]));
   }

  function cadastrar($conexao, $nomeDaTabelaAluno, $idUsuarioGerado)
   {
   $this->idUsuario = $idUsuarioGerado;

   $sql = "INSERT $nomeDaTabelaAluno VALUES(
            '$this->idUsuario',
            '$this->curso')";

   $conexao->query($sql) or die($conexao->error);
   } 
 }
?>