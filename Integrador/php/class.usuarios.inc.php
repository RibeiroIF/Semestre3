<?php
 class Usuarios {
  public $nome;
  public $email;
  public $senha;
  public $senha2;
  public $dataCadastro;
 
  function receberDadosForm($conexao)
   {
   // O ID é nulo no cadastro pois é auto_increment no banco
   $this->nome         = trim($conexao->escape_string($_POST["nome"]));
   $this->email        = trim($conexao->escape_string($_POST["email"]));
   // Criptografando a senha em SHA-256 para segurança antes de salvar
   $this->senha        = hash("sha256", trim($conexao->escape_string($_POST["senha"])));
   $this->senha2       = hash("sha256", trim($conexao->escape_string($_POST["senha2"])));
   
   if($this->senha = $this->senha2){
    $this->senha        = password_hash($this->senha, PASSWORD_ARGON2I);
   }

   $this->dataCadastro = date("Y-m-d"); // Capta a data atual do sistema
   }

  function cadastrar($conexao, $usuario)
   {
   $sql = "INSERT $usuario VALUES(
             null,
            '$this->nome',
            '$this->email',
            '$this->senha',
            '$this->dataCadastro')";

   $conexao->query($sql) or die($conexao->error);
   
   // Retorna o ID gerado para ser usado pelas tabelas filhas (aluno/admin)
   return $conexao->insert_id; 
   } 
 }
?>