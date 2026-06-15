<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema Norteador</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/display.css">
    <link rel="stylesheet" href="../css/form.css">
</head>

<body>
    <div class="container">
        <header class="header">
          <h1 class="title"> LavaCar Aquaman </h1>
            <nav class="navbar">
              <ul>
                <li> <a href="homepage.php" class="card" id="return"> Voltar à Página Inicial </a> </li>
                <li> <a href="cadastro-veiculo.php" class="card"> Cadastrar Veículo </a> </li>
                <li> <a href="login-cliente.php" class="card"> Login do Cliente </a> </li>
                <li> <a href="login-admin.php" class="card"> Login Administrador </a> </li>
              </ul>
            </nav>
        </header>

        <!--Display de fotos-->
        <div class="espaco-imagem">
          <div class="slider">
            <div class="display">
              <img src="../imagens/lavacao-imagem-1.jpg" alt="foto lavação externa">
              <img src="../imagens/lavacao-imagem-2.jpg" alt="foto lavação completa">
              <img src="../imagens/lavacao-imagem-3.jpeg" alt="foto enceramento">
              <img src="../imagens/lavacao-imagem-4.jpg" alt="foto lavação interna">
              <img src="../imagens/lavacao-imagem-5.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-6.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-7.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-8.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-9.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-10.jpeg" alt="foto lavação">
            </div>
            <div aria hidden class="display">
              <img src="../imagens/lavacao-imagem-1.jpg" alt="foto lavação externa">
              <img src="../imagens/lavacao-imagem-2.jpg" alt="foto lavação completa">
              <img src="../imagens/lavacao-imagem-3.jpeg" alt="foto enceramento">
              <img src="../imagens/lavacao-imagem-4.jpg" alt="foto lavação interna">
              <img src="../imagens/lavacao-imagem-5.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-6.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-7.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-8.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-9.jpeg" alt="foto lavação">
              <img src="../imagens/lavacao-imagem-10.jpeg" alt="foto lavação">
            </div>
          </div>
        </div>

        <!--Display de texto explicativo-->
        <div class="enunciado">
          <h2 class="enunciado-titulo"> Espaço para <span class="subtitle">Cadastro do Cliente</span> </h2> <br>
          <p class="enunciado-texto"> Este espaço é dedicado para realizar o cadastro do(a) cliente! <br>
          Para realizar este cadastro serão requeridas algumas informações, entre elas: <br> <br>
            <ul class="enunciado-lista">
              <li> Informações pessoais 
                <ul>
                  <li class="sub-item"> Nome </li>
                  <li class="sub-item"> Endereço </li>
                  <li class="sub-item"> E-mail </li>
                  <li class="sub-item"> Telefone </li>
                </ul>
              </li>
              <li> Informações do servidor 
                <ul>
                  <li class="sub-item"> Login </li>
                  <li class="sub-item"> Senha </li>
                </ul>
              </li>
            </ul> </p>
        </div>

        <!--Seção principal-->
        <main class="form">
          <form action="dados-cliente.php" method="post" id="form-cliente">
            <fieldset>
              <legend> Cadastro do Cliente: </legend>
              
              <div class="form-infos">
                <label for="nome" class="alinha"> Nome: </label>
                <input type="text" name="nome" id="nome" placeholder="Insira seu nome aqui..."> <br>

                <label for="endereco" class="alinha"> Endereço: </label>
                <input type="text" name="endereco" id="endereco"> <br>

                <label for="email" class="alinha"> E-mail: </label>
                <input type="email" name="email" id="email"> <br>

                <label for="telefone" class="alinha"> Telefone: </label>
                <input type="tel" name="telefone" id="telefone"> <br>
              </div>

              <div class="infosCadastro">
                <label for="user" class="alinha"> Usuário: </label>
                <input type="text" name="user" id="user"> <br>

                <label for="password" class="alinha"> Senha: </label>
                <input type="password" name="password" id="password"> <br>
              </div>

              <button form="form-cliente" type="submit" class="button" name="button-cliente"> Cadastrar Cliente </button>
            </fieldset>
          </form>
        </main>
  </div>

  <footer class="footer">
     <p> Desenvolvimento web - CTDS - Web Design, turma do semestre 2025/1 <br>
     Copyright &copy;2025 - todos os direitos reservados. Proibida a reprodução parcial ou total do conteúdo presente nesta aplicação. <br>

     <a href="contato-empresa.html"> Entre em contato conosco </a> 
     </p>
  </footer>

</body>
</html>