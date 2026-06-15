<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema Norteador</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/display.css">
</head>

<body>
    <div class="container">
        <header class="header">
          <h1 class="title"> LavaCar Aquaman </h1>
            <nav class="navbar">
              <ul>
                <li> <a href="cadastro-cliente.php" class="card"> Cadastrar Cliente </a> </li>
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
          <h2 class="enunciado-titulo"> Seja bem vindo(a) ao <span class="subtitle">LavaCar Aquaman!</span> </h2> <br> 
          <p class="enunciado-texto"> Nós somos o <span>LavaCar Aquaman</span>, uma empresa emergente no mercado de lavação de carros
          focada em entregar o melhor para você, cliente, tanto em termos de qualidade de serviço quanto atendimento. <br> 
          
          Neste Website você terá acesso às funcionalidades referentes à utilização de nosso serviço, assim como instruções para 
          uso de cada uma delas. <br> 
          
          Entre estas funcionalidades, estão presentes: <br> <br>
            <div class="enunciado-lista">
              <ul>
                <li> Cadastro de Clientes no sistema  </li>
                <li> Cadastro de Veículos para cada Cliente </li>
                <li> Login para o cliente na aplicação </li>
                <li> Login para o administrador na aplicação </li>
                <li> Outros serviços </li>
              </ul> 
            </div>
          </p>
        </div>
  </div>

  <footer class="footer">
     <p> Desenvolvimento web - CTDS - Web Design, turma do semestre 2025/1 <br>
     Copyright &copy;2025 - todos os direitos reservados. Proibida a reprodução parcial ou total do conteúdo presente nesta aplicação. <br>

     <a href="contato-empresa.html"> Entre em contato conosco </a> 
     </p>
  </footer>
</body>
</html>