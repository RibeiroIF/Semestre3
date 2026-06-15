<?php
 class Alunos {
  public $idUsuario;
 
/*   function receberDadosForm($conexao)
   {
   // O idUsuario será capturado após a inserção na tabela pai (Usuarios)
   $this->curso = trim($conexao->escape_string($_POST["curso"]));
   } */

  function cadastrar($conexao, $aluno, $idUsuarioGerado)
   {
   $this->idUsuario = $idUsuarioGerado;

   $sql = "INSERT $aluno VALUES(
            '$this->idUsuario')";

   $conexao->query($sql) or die($conexao->error);
   } 
 }
?>