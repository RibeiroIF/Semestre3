<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="entrega.css">
  <title> Exercício para entrega ProgWeb2 </title>
</head>

<body>
  <header> Exercício para entrega - ProgWeb 2 </header>

  <div class="blocogeral">
    <div class="blocoformulario">
      <form action="main.php" method="post">
        <fieldset>
          <legend> Projetos para a Empresa PromptWare </legend>

          <div class="formulario">
            <label class="alinha"> ID do projeto: </label>
            <input type="number" name="id" autofocus min="0" max="9999999"> <br>

            <label class="alinha"> Orçamento (R$ - 1m máx): </label>
            <input type="number" name="orcamento" min="0" max="1000000"> <br>

            <label class="alinha"> Data de início: </label>
            <input type="date" name="dataInicio"> <br>

            <label class="alinha"> Número de horas necessárias (10000h máx): </label>
            <input type="number" name="horas" min="0" max="10000"> <br>
          </div>

          <div class="buttons">
            <button name="cadastrar" class="button"> Cadastrar </button>
            <button name="listar" class="button"> Listar </button>
            <button name="mostrarEspecifico" class="button"> Mostrar projetos 2020+ </button>
            <button name="deletar" class="button"> Deletar projetos < 100h e < R$1000 </button>
          </div>

        </fieldset>
      </form>
    </div>
    <h1 class="enunciadoResposta"> Resultado da Ação </h1>
    <div class="blocoresposta">
        <?php 
          require "database.php";
          require "empresa.php";

          //EM CASA COLOQUEI UMA SENHA NO TERCEIRO ESPAÇO DO PARÂMETRO PARA MEU SERVIDOR PRÓPRIO, PORÉM NO ENVIO JÁ RETIREI
          $banco = new BancoDeDados("localhost", "root", "", "BancoPromptware", "projetos");

          $conexao = $banco->criarConexao();
          $banco->criarBanco($conexao);
          $banco->abrirBanco($conexao);
          $banco->definirCharset($conexao);
          $banco->criarTabelaProjetos($conexao); 
          $empresa = new Empresa(); 

          //FUNÇÃO PARA CADASTRAR OS DADOS INSERIDOS
          if(isset($_POST['cadastrar'])){
            $empresa->receberDados($conexao);
            $empresa->cadastrar($conexao, $banco->projetos);
          }

          //FUNÇÃO PARA LISTAR OS PROJETOS POR ID E ORÇAMENTO
          if(isset($_POST['listar'])) {$empresa->listarIdOrcamento($conexao, $banco->projetos);}

          //FUNÇÃO PARA MOSTRAR A QUANTIDADE DE PROJETOS COM DATA DE INÍCIO APÓS 01/01/2020
          if(isset($_POST['mostrarEspecifico'])) {$empresa->listar2020PraCima($conexao, $banco->projetos);}

          //FUNÇÃO PARA DELETAR OS PROJETOS COM MENOS DE 100 HORAS E R$1.000 REAIS
          if(isset($_POST['deletar'])) {$empresa->deletarMenosCemHorasEMilReais($conexao, $banco->projetos);}

          ?>
    </div>
  </div>
</body>
</html>