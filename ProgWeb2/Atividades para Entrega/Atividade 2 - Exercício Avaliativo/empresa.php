<?php
  class Empresa{
    public $id;
    public $orcamento;
    public $dataInicio;
    public $horas;

    function receberDados($conexao){
      $this->id = trim($conexao->escape_string($_POST["id"]));
      $this->orcamento = trim($conexao->escape_string($_POST["orcamento"]));
      $this->dataInicio = trim($conexao->escape_string($_POST["dataInicio"]));
      $this->horas = trim($conexao->escape_string($_POST["horas"]));
    }

    //A) FUNÇÃO REFERENTE AO CADASTRO DOS DADOS DO PROJETO NA EMPRESA: PRIMEIRA QUESTÃO
    function cadastrar($conexao, $projetos){
      $sql = "INSERT $projetos VALUES(
            $this->id,
            $this->orcamento,
            '$this->dataInicio',
            '$this->horas')";

      $conexao->query($sql) or die($conexao->error);
      echo "<h3> Projeto cadastrado com sucesso!! </h3>";
   }

   //B) FUNÇÃO REFERENTE À LISTAGEM DO ID E ORÇAMENTO DOS PROJETOS DA EMPRESA: SEGUNDA QUESTÃO
   function listarIdOrcamento($conexao, $projetos){
   echo "<table>
          <caption> Projetos listados pela empresa Promptware </caption>
          <tr>
           <th> ID </th>
           <th> Orçamento </th>
          </tr>";

   $sql = "SELECT * FROM $projetos";
   $resultado = $conexao->query($sql) or die($conexao->error);

   while($vetorRegistro = $resultado->fetch_array()){
    $id = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");
    $orcamento = htmlentities($vetorRegistro[1], ENT_QUOTES, "UTF-8");
    $orcamentoFormatado = number_format($orcamento, 2, ",", ".");

    echo "<tr>
           <td> $id </td>
           <td> R$ $orcamentoFormatado </td>
          </tr>";
    
    }
   echo "</table>";
   }

   //C) FUNÇÃO REFERENTE À LISTAGEM DE PROJETOS QUE TIVERAM INÍCIO APÓS O PERÍODO DE 01/01/2020: TERCEIRA QUESTÃO
   function listar2020PraCima($conexao, $projetos){
    $sql = "SELECT id FROM $projetos WHERE data_inicio > '2020-01-01'";
    $resultado = $conexao->query($sql) or die($conexao->error);

    if($conexao->affected_rows == 0){
      die("<h3> Não há nenhum projeto que teve início após a data de 01/01/2020 </h3>");
    }
    else{
      $sql = "SELECT COUNT(*) FROM $projetos WHERE data_inicio > '2020-01-01'";
      $resultado = $conexao->query($sql) or die($conexao->error);
      $vetorRegistro = $resultado->fetch_array();
      $quantidadeProjetos  = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");
      echo "<h3> Atualmente a empresa possui <span> $quantidadeProjetos </span> projeto(s) iniciado(s) após o dia 01/01/2020 </h3>";
    }

    }

   //D) FUNÇÃO REFERENTE À EXCLUSÃO DE PROJETOS COM MENOS DE 100 HORAS E R$1000 INVESTIDOS: QUARTA QUESTÃO
   function deletarMenosCemHorasEMilReais($conexao, $projetos){
    //ESTA LINHA ABAIXO FOI APENAS UM TESTE QUE MANTIVE PARA VERIFICAR SE OS PROJETOS SELECIONADOS ESTAVAM CORRETOS DE ACORDO COM O ENUNCIADO
    //$sql = "SELECT * FROM $projetos WHERE orcamento < 1000 AND horas < 100";

    //PARA DELETAR DADOS O SQL REALIZA UM BLOQUEIO EM RELAÇÃO AO MODO "SAFE UPDATE", PARA ISSO HÁ DUAS FORMAS DE CONTORNAR:
    //1) UTLIZANDO CONSTRAINT E DELETE CASCADE
    //2) DESATIVANDO "SAFE UPDATE" ATRAVÉS DE COMANDO INTERNO
    //EU OPTEI PELA SEGUNDA OPÇÃO:

    $sql = "SET SQL_SAFE_UPDATES = 0;";
    $conexao->query($sql) or die($conexao->error);

    $sql = "DELETE FROM $projetos WHERE orcamento < 1000 AND horas < 100;";
    $conexao->query($sql) or die($conexao->error);

    $sql = "SET SQL_SAFE_UPDATES = 1;";
    $conexao->query($sql) or die($conexao->error);

    echo "<h3> Projetos deletados com sucesso!! </h3>";
    }
   }

?>