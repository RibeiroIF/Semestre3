<?php
 class Usuarios {
  public $id;
  public $nome;
  public $email;
  public $senha;
  public $dataCadastro;
  public $campus;
 
  function receberDadosForm($conexao)
   {
   // O ID é nulo no cadastro pois é auto_increment no banco
   $this->id           = null; 
   $this->nome         = trim($conexao->escape_string($_POST["nome"]));
   $this->email        = trim($conexao->escape_string($_POST["email"]));
   // Criptografando a senha em SHA-256 para segurança antes de salvar
   $this->senha        = hash("sha256", trim($conexao->escape_string($_POST["senha"])));
   $this->dataCadastro = date("Y-m-d"); // Capta a data atual do sistema
   $this->campus       = trim($conexao->escape_string($_POST["campus"]));
   }

  function cadastrar($conexao, $nomeDaTabelaUsuario)
   {
   $sql = "INSERT $nomeDaTabelaUsuario VALUES(
             null,
            '$this->nome',
            '$this->email',
            '$this->senha',
            '$this->dataCadastro',
            '$this->campus')";

   $conexao->query($sql) or die($conexao->error);
   
   // Retorna o ID gerado para ser usado pelas tabelas filhas (aluno/admin)
   return $conexao->insert_id; 
   } 
 }
?>