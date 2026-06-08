<?php
  class Jogos{
    public $nome;
    public $preco;
    public $genero;

    function receberDadosForm($conexao){
      $this->nome   = trim($conexao->escape_string($_POST["nome"]));
      $this->preco  = trim($conexao->escape_string($_POST["preco"]));
      $this->genero = trim($conexao->escape_string($_POST["genero"]));
    }

    //QUESTÃO A: SALVAR OS DADOS NO BANCO (O ID SENDO UM INT DE AUTO_INCREMENT)
    function cadastrar($conexao, $jogos){

      $sql = "INSERT $jogos VALUES(
              NULL,
            '$this->nome',
            $this->preco,
            '$this->genero')";

      $conexao->query($sql) or die($conexao->error);
    }

    //QUESTAO B: CALCULAR A MÉDIA DE PREÇO DOS JOGOS DE GÊNERO AVENTURA
    function calcularMedia($conexao, $jogos) {
      $sql = "SELECT AVG(preco) FROM $jogos WHERE genero = 'Aventura'";
      $resultado = $conexao->query($sql) or die($conexao->error);

      $vetorRegistro = $resultado->fetch_array();

      $mediaPrecoAventura = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");
      $mediaPrecoAventuraFormatado = number_format($mediaPrecoAventura, 2, ",", ".");

      echo "<p> A média de preço dos jogos de gênero <span> Aventura </span> é de = R$<span> $mediaPrecoAventuraFormatado </span> </p>";
    }

    //QUESTAO C: ALTERAR VALOR DO JOGO ESCOLHIDO
    function alterarValor($conexao, $jogos){
      $nomePesquisado = trim($conexao->escape_string($_POST["nome-alterar"]));
      $novoPreco      = trim($conexao->escape_string($_POST["preco-alterar"]));

      $sql = "UPDATE $jogos SET preco = $novoPreco WHERE nome = '$nomePesquisado'";

      $conexao->query($sql) or die($conexao->error);

      if($conexao->affected_rows == 0){
        echo "<p> O nome do jogo pesquisado não existe. </p>";
      }
      else{
        echo "<p> O preço do jogo <span> $nomePesquisado </span> foi alterado com sucesso!! </p>";
      } 
    }
  }
?>