<?php
 class BancoDeDados{
  public $servidor;
  public $user;
  public $senha;

  public $dbIntegrador;

  public $usuario;
  public $administrador;
  public $aluno;
  public $categoria;
  public $anuncio;
  public $avaliacao;
  public $denuncia;
  public $feedback;

  function __construct($servidor, $user, $senha, $dbIntegrador, $usuario, $administrador, $aluno, $categoria, $anuncio, $avaliacao, $denuncia, $feedback)
   {
   $this->servidor = $servidor;
   $this->user = $user;
   $this->senha = $senha;

   $this->dbIntegrador = $dbIntegrador;

   $this->usuario = $usuario;
   $this->administrador = $administrador;
   $this->aluno = $aluno;
   $this->categoria = $categoria;
   $this->anuncio = $anuncio;
   $this->avaliacao = $avaliacao;
   $this->denuncia = $denuncia;
   $this->feedback = $feedback;

   }

  function criarConexao(){
    $conexao = new mysqli($this->servidor, $this->user, $this->senha) OR die($conexao->error);
    return $conexao;
  }

  function criarBanco($conexao){
    $sql = "CREATE DATABASE IF NOT EXISTS $this->dbIntegrador";
    $conexao->query($sql) or die($conexao->error);
  }

  function abrirBanco($conexao){
    $conexao->select_db($this->dbIntegrador);
  }

  function definirCharset($conexao){
    $conexao->set_charset("utf8");
  }

  function criarTabelaUsuario($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->usuario (
            id int not null auto_increment,
            nome varchar(40),
            email varchar(60),
            senha varchar(128),
            data_cadastro date,
            constraint pk_usuario primary key(id)
            ) ENGINE=innoDB;";

   $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaAdministrador($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->administrador (
            id_usuario int not null auto_increment,
            ocupacao varchar(20) not null,
            constraint pk_administrador primary key(id_usuario),
            constraint fk_administrador_usuario foreign key(id_usuario) references $this->usuario (id)
                on delete cascade
                on update cascade
            ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaAluno($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->aluno (
           id_usuario int not null auto_increment,
           constraint pk_aluno primary key(id_usuario),
           constraint fk_aluno_usuario foreign key(id_usuario) references $this->usuario (id)
              on delete cascade
              on update cascade
              ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaCategoria($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->categoria (
           id int not null auto_increment,
           nome varchar(20),
           descricao varchar(60),
           constraint pk_categoria primary key(id)
           ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaAnuncio($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->anuncio (
           id int not null auto_increment,
           titulo varchar(30),
           descricao varchar(200),
           preco decimal(10,2) not null,
           imagem int not null,
           status_anuncio enum ('EM ABERTO', 'EM NEGOCIACAO', 'VENDIDO') default 'EM ABERTO',
           data_publicacao date,
           data_expiracao date,
           visualizacoes int,
           denuncias int,
           id_categoria int not null,
           id_vendedor int not null,
           constraint pk_anuncio primary key(id),
           constraint fk_anuncio_categoria foreign key(id_categoria) references $this->categoria(id),
           constraint fk_anuncio_usuario foreign key(id_vendedor) references $this->usuario(id)
              on delete cascade
              on update cascade
    ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaAvaliacao($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->avaliacao (
           id int not null auto_increment,
           nota int not null,
           comentario varchar(200),
           data_avaliacao date,
           resposta_vendedor text,
           id_anuncio int not null,
           id_comprador int not null,
           constraint pk_avaliacao primary key(id),
           constraint fk_avaliacao_anuncio foreign key(id_anuncio) references $this->anuncio(id),
           constraint fk_avaliacao_usuario foreign key(id_comprador) references $this->usuario(id)
              on delete cascade
              on update cascade
              ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaDenuncia($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->denuncia (
           id int not null auto_increment,
           comentario varchar(200),
           data_denuncia date,
           id_anuncio int not null,
           id_comprador int not null,
           constraint pk_denuncia primary key(id),
           constraint fk_denuncia_anuncio foreign key(id_anuncio) references anuncio(id),
           constraint fk_denuncia_usuario foreign key(id_comprador) references usuario(id)
              on delete cascade
              on update cascade
           ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function criarTabelaFeedback($conexao){
    $sql = "CREATE TABLE IF NOT EXISTS $this->feedback (
           id int not null auto_increment,
           descricao text,
           data_feedback date,
           id_usuario int not null,
           constraint pk_feedback primary key(id),
           constraint fk_feedback_usuario foreign key(id_usuario) references usuario(id)
              on delete cascade
              on update cascade
           ) ENGINE=innoDB;";

    $conexao->query($sql) OR die($conexao->error);
  }

  function desconectar($conexao){
    $conexao->close();
  }
}