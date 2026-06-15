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
          <h2 class="enunciado-titulo"> Espaço para <span class="subtitle">Feedbacks</span> </h2> <br> 
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
          <form action="contato-empresa.php" method="post" id="contato-empresa">
            <fieldset>
              <legend> Contato com a Empresa: </legend>

              <div class="form-infos">
                <label for="contatoEmpresa" class="alinha"> Espaço para sugestões </label>
                <textarea name="contatoEmpresa" id="idContato" placeholder="Suas sugestões aqui: "></textarea> <br>

                <button form="contatoEmpresa" type="submit" class="button" name="contatoEmpresa"> Enviar Sugestões </button>
              </div>
            </fieldset>
          </form>
        </main>
    </div>

    <footer class="footer">
     <p> Desenvolvimento web - CTDS - Web Design, turma do semestre 2025/1 <br>
     Copyright &copy;2025 - todos os direitos reservados. Proibida a reprodução parcial ou total do conteúdo presente nesta aplicação. <br>

     <a href="homepage.html"> Retorne à página inicial </a> 
     </p>
  </footer>

</body>
</html>