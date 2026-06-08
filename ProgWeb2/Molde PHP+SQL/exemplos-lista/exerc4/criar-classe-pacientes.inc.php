<?php
 class Pacientes {
  public $nome;
  public $crmMedico;
  public $dataInternacao;
 
  function receberDadosForm($conexao)
   {
   $this->nome           = trim($conexao->escape_string($_POST["nome-paciente"]));
   $this->crmMedico      = trim($conexao->escape_string($_POST["crm-atendimento"]));
   $this->dataInternacao = trim($conexao->escape_string($_POST["data"]));
   }

  function cadastrar($conexao, $nomeDaTabela2)
   {
   $sql = "INSERT $nomeDaTabela2 VALUES(
             null,
            '$this->nome',
            '$this->dataInternacao',
            '$this->crmMedico')";

   $conexao->query($sql) or die($conexao->error);
   } 

  function exibirRelatorio($conexao, $nomeDaTabela2)
   {
   $dataPesquisada = trim($conexao->escape_string($_POST["pesquisa-data"]));

   //vamos criar a consulta que seleciona todos os pacientes com a data de internação pesquisada
   $sql = "SELECT * FROM $nomeDaTabela2 WHERE data_internacao = '$dataPesquisada'";

   $resultado = $conexao->query($sql) or die($conexao->error);

   $vetorData = explode("-", $dataPesquisada);
   $dataFormatada = $vetorData[2] . "/" . $vetorData[1] . "/" . $vetorData[0];

   if($conexao->affected_rows == 0)
    {
    die("<p> Nenhum paciente cadastrado na base de dados foi encontrado com data de internação <span> $dataFormatada </span>. Tente novamente! </p>");
    }

   else
    {
    //aqui, montamos toda a estrutura da tabela para exibir as informações dos clientes que deram entrada, na clínica, na data pesquisada

    echo "<table>
           <caption> Relatório dos pacientes com internação na data <span> $dataPesquisada </span> </caption>

           <tr>
            <th> ID </th>
            <th> Paciente </th>
            <th> Data de internação </th>
            <th> CRM de atendimento </th>
          </tr>";

   while($vetorRegistro = $resultado->fetch_array())
    {
    $id       = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");
    $paciente = htmlentities($vetorRegistro[1], ENT_QUOTES, "UTF-8");
    $data     = htmlentities($vetorRegistro[2], ENT_QUOTES, "UTF-8");
    $crm      = htmlentities($vetorRegistro[3], ENT_QUOTES, "UTF-8");

    $vetorData = explode("-", $data);
    $dataFormatada = $vetorData[2] . "/" . $vetorData[1] . "/" . $vetorData[0];

    echo "<tr>
           <td> $id </td>
           <td> $paciente </td>
           <td> $dataFormatada </td>
           <td> $crm </td>
          </tr>";
    
    }
   echo "</table>";
   }
  }
 }