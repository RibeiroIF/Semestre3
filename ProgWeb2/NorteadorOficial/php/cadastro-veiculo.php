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
                <li> <a href="cadastro-cliente.php" class="card"> Cadastrar Cliente </a> </li>
                 <li> <a href="homepage.php" class="card" id="return"> Voltar à Página Inicial </a> </li>
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
          <h2 class="enunciado-titulo"> Espaço para <span class="subtitle">Cadastro do Veículo</span> </h2> <br> 
          <p class="enunciado-texto"> Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure dolor sunt voluptatum mollitia
             accusamus hic corporis molestias sint, autem officia, voluptate, aperiam est. Hic maiores porro odit, voluptatum harum
             recusandae quaerat veniam placeat consectetur. Nobis omnis reiciendis veniam quas dolorem totam voluptatem dicta
             aspernatur et odio! Quam beatae nisi odio vel quisquam architecto impedit nulla dolores possimus quibusdam temporibus
             id aliquam, rerum ratione in! Cumque eum esse dolor officiis quas! Aliquam eum labore accusantium magnam numquam, 
             dolores, doloremque, adipisci fugiat dolorem vero expedita beatae sed sunt corporis ut minima quaerat atque saepe 
             quam. Quia amet alias aut quis quidem minima? </p>
        </div>

        <!--Seção principal-->
        <main class="form">
          <form action="dados-veiculo.php" method="post" id="form-veiculo">
            <fieldset>
              <legend> Cadastro do Veículo: </legend>

              <div class="form-infos">
                <label for="fabricante" class="alinha"> Fabricante: </label>
                <input type="text" name="fabricante" id="fabricante" autofocus placeholder="Nome do fabricante aqui..."> <br>
                
                <label for="modelo" class="alinha"> Modelo: </label>
                <input type="text" name="modelo" id="modelo"> <br>

                <label for="placa" class="alinha"> Placa: </label>
                <input type="text" name="placa" id="placa"> <br>
              </div>

              <button form="formVeiculo" type="submit" class="button" name="buttonVeiculo"> Cadastrar Veículo </button>
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