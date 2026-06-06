<?php
 class Categorias {
  public $id;
  public $nome;
  public $descricao;
 
  function receberDadosForm($conexao)
   {
   $this->id        = null; 
   $this->nome      = trim($conexao->escape_string($_POST["nome-categoria"]));
   $this->descricao = trim($conexao->escape_string($_POST["descricao-categoria"]));
   }

  function cadastrar($conexao, $nomeDaTabelaCategoria)
   {
   $sql = "INSERT $nomeDaTabelaCategoria VALUES(
             null,
            '$this->nome',
            '$this->descricao')";

   $conexao->query($sql) or die($conexao->error);
   } 

  // Método para renderizar os <option> dentro do formulário do index.html
  function listarOptions($conexao, $nomeDaTabelaCategoria)
   {
   $sql = "SELECT id, nome FROM $nomeDaTabelaCategoria ORDER BY nome ASC";
   $resultado = $conexao->query($sql) or die($conexao->error);

   while($vetorRegistro = $resultado->fetch_array())
    {
    $id   = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");
    $nome = htmlentities($vetorRegistro[1], ENT_QUOTES, "UTF-8");
    echo "<option value='$id'>$nome</option>";
    }
   }
 }
?>