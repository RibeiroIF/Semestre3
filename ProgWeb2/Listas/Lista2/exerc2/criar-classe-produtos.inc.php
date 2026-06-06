<?php
 //Agora, para finalizarmos a segunda parte da aplicação envolvendo banco de dados, vamos criar a classe Alunos.  Esta classe conterá métodos e propriedades necessários ao PHP para representarmos, no MySQL, cada aluno sendo cadastrado. Esta classe será responsável por implementar cada operação solicitada no exercício
 class Produtos {
  public $id;
  public $preco;
  public $estoque;
  public $classificacao;
  public $descricao;

  //implementando o método que recebe os dados do formulário e os atribui às propriedades do objeto aluno
  function receberDadosForm($conexao)
   {
   //CUIDADO COM ESTA OPERAÇÃO: se não codificada corretamente, há a possibilidade de execução do ataque conhecido como injeção de SQL
   $this->id            = trim($conexao->escape_string($_POST["id"]));
   $this->preco         = trim($conexao->escape_string($_POST["preco"]));
   $this->estoque       = trim($conexao->escape_string($_POST["estoque"]));
   $this->classificacao = trim($conexao->escape_string($_POST["classific"]));
   $this->descricao     = trim($conexao->escape_string($_POST["descricao"]));
   }

  //método que, de verdade, grava os dados na tabela do banco de dados
  function cadastrar($conexao, $nomeDaTabela)
   {
   $sql = "INSERT $nomeDaTabela VALUES(
            '$this->id',
            $this->preco,
            $this->estoque,
            '$this->classificacao',
            '$this->descricao')";

   $conexao->query($sql) or die($conexao->error);
   }

  //método para percorrer o banco de dados e tabular os dados de cada aluno na página web
  function tabularDados($conexao, $nomeDaTabela)
   {
   echo "<table>
          <caption> Dados dos produtos cadastrados no banco de dados </caption>
          <tr>
           <th> ID </th>
           <th> Preço </th>
           <th> Qtd em estoque </th>
           <th> Classificação </th>
           <th> Descrição </th>
          </tr>";

   //vamos criar uma estrutura que permita que o código em PHP percorra todo o conjunto de dados enviado pelo banco na consulta SELECT. Esta estrutura pode ser comparada a uma matriz e permite que o PHP receba os dados de cada aluno e mostre-os no formato de tabela, na página web
   $sql = "SELECT * FROM $nomeDaTabela";

   //recebemos o resultado do select e o colocamos na "matriz" $resultado
   $resultado = $conexao->query($sql) or die($conexao->error);

   while($vetorRegistro = $resultado->fetch_array())
    {
    //para veitarmos o ataque do tipo XSS, usamos o comando de sanitização a seguir
    $matric = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");
    $aluno  = htmlentities($vetorRegistro[1], ENT_QUOTES, "UTF-8");
    $media  = htmlentities($vetorRegistro[2], ENT_QUOTES, "UTF-8");

    echo "<tr>
           <td> $matric </td>
           <td> $aluno </td>
           <td> $media </td>
          </tr>";
    
    }
   echo "</table>";
   }

  //vamos implementar o método que mostra o número de alunos aprovados na UC
  function contarAprovados($conexao, $nomeDaTabela) 
   {
   $sql = "SELECT COUNT(*) FROM $nomeDaTabela WHERE media >= 6";
   $resultado = $conexao->query($sql) or die($conexao->error);

   $vetorRegistro = $resultado->fetch_array();

   $numeroAlunosAprovados = htmlentities($vetorRegistro[0], ENT_QUOTES, "UTF-8");

   echo "<p> Número de alunos aprovados em PRWII e registrados no banco de dados = <span> $numeroAlunosAprovados </span> aluno(s) </p>";
   }
 }