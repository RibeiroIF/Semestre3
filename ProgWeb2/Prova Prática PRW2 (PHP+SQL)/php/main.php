<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/main.css">
  <link rel="stylesheet" href="../css/form.css">
  <title> Exercício para entrega ProgWeb2 </title>
</head>

<body>
  <header> Prova Prática PRW2 - PQP + SQL </header>

  <div class="blocogeral">
    <div class="blocoformulario">
      <form action="main.php" method="post" id="form-registrar">
        <fieldset>
          <legend> Inserir Jogo </legend>

          <div class="formulario">
            <label class="alinha"> Nome do jogo: </label>
            <input type="text" name="nome" autofocus> <br>

            <label class="alinha"> Valor do jogo: </label>
            <input type="number" name="preco" min="0" max="550.00" step="0.01"> <br>

            <label class="alinha"> Gênero do jogo: </label>
            <select name="genero" id="genero">
              <option value="rpg"> RPG </option>
              <option value="acao"> Ação </option>
              <option value="aventura"> Aventura </option> 
            </select> <br>

          </div>
        </fieldset>
      </form>

      <form action="main.php" method="post" id="form-alterar">
        <fieldset>
          <legend> Alterar Valor </legend>

          <div class="formulario">
            <label class="alinha"> Novo nome: </label>
            <input type="text" name="nome-alterar" autofocus> <br>

            <label class="alinha"> Novo preço: </label>
            <input type="number" name="preco-alterar" min="0" max="550.00" step="0.01"> <br>

        </fieldset>
      </form>

      <div class="botoes">
        <button form="form-registrar" name="salvar" class="button"> Salvar </button>
        <button form="form-registrar" name="media" class="button"> Calcular Média Aventura </button>
        <button form="form-alterar" name="alterar" class="button"> Alterar Valor </button>
      </div>
    </div>
    <div class="blocoresposta">
      <h1> Resultado </h1>
        <?php 
          require "criarBDD-prova.php";
          require "criarClasseJogos.php";

          $banco = new BancoDeDados("localhost", "root", "", "CTDS", "jogos");

          $conexao = $banco->criarConexao();
          $banco->criarBanco($conexao);
          $banco->abrirBanco($conexao);
          $banco->definirCharset($conexao);
          $banco->criarTabela($conexao); 
          $jogos = new Jogos(); 

          //QUESTÃO 1: CADASTRAR
          if(isset($_POST["salvar"])){
            $jogos->receberDadosForm($conexao);
            $jogos->cadastrar($conexao, $banco->jogos);
            echo "<h2> Jogo cadastrado!! </h2>";
          }
          //QUESTÃO 2: CALCULAR MÉDIA
          if(isset($_POST["media"])){
            $jogos->calcularMedia($conexao, $banco->jogos);
            echo "<h2> Média calculada!! </h2>";
          }
          //QUESTÃO 3: MUDAR VALOR DO JOGO
          if(isset($_POST["alterar"])){
            $jogos->alterarValor($conexao, $banco->jogos);
            echo "<h2> Valor do jogo alterado!! </h2>";
          } 

          ?>
    </div>
  </div>
</body>
</html>