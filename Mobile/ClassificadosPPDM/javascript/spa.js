    let telaAnterior = 'tela-home'
    let telaAtual = 'tela-home'

   function navegar(destino){
       let telas = document.getElementsByClassName('tela')
       Array.from(telas).forEach(element => {
           element.classList.remove('show')
           element.classList.add('collapse')
       });
       document.getElementById(destino).classList.remove('collapse')
       document.getElementById(destino).classList.add('show')
       telaAnterior = telaAtual
       telaAtual = destino
    }

    function voltar() {
        navegar(telaAnterior)
        document.getElementById("botao-cadastro").classList.remove("collapse");
        document.getElementById("botao-cadastro").classList.add("show");
    }

   function mostrarDetalhes(produto, imagem, categoria, preco, descricao, nota, avaliacoes){
        navegar('tela-produto')
        let detalhes = document.getElementById('detalhes-produto')
        detalhes.innerHTML =`
            <div class="row g-3">
                <div class="col-md-4 text-center">
                <img src="${imagem}" class="img-fluid" alt="${produto}">
                </div>
                <div class="col-md-8">
                <h2>${produto}</h2>
                <p><strong>Categoria:</strong> ${categoria}</p>
                <p><strong>Preço:</strong> R$ ${preco}</p>
                <p><strong>Descrição:</strong> ${descricao}</p>
                <p><strong>Avaliação:</strong> ${nota} ⭐ (${avaliacoes} avaliações)</p>
                </div>
            </div>
        `
   }

   // Função que carrega produtos baseado em uma categoria escolhida
async function carregarPorCategoria(categoria) {

  const categoriaTratada = categoria.replace("’", "'");

   try {
       let url;
       const telaLista = document.getElementById("tela-home");


       // Define URLs conforme categoria selecionada
       if (categoria === 'todos') {
           url = 'http://localhost:3000/products';
       } else {
           url = `http://localhost:3000/products?category=${encodeURIComponent(categoriaTratada)}`;
       }


       // Faz requisições 
       const response = await axios.get(url);


       const produtos = response.data;


       // Renderiza os produtos na tela principal
       telaLista.innerHTML = "";
       produtos.forEach(produto => {
           const card = document.createElement("div");
           card.className = "col";
           card.innerHTML = `
               <div class="card h-100" onclick="abrirDetalhes(${produto.id})">
               <img src="${produto.image.replace(".jpg", ".png")}" class="card-img-top p-3" style="height:250px; object-fit:contain;">
               <div class="card-body">
                   <h5 class="card-title">${produto.title}</h5>
                   <p class="card-text">R$ ${produto.price}</p>
               </div>
               </div>
           `;
           telaLista.appendChild(card);
       });


       navegar('tela-home')
   } catch (error) {
       console.error("Erro ao carregar produtos:", error);
   }
}
carregarPorCategoria('todos')

// Função que carrega detalhes específicos de um produto
async function abrirDetalhes(id) {
  try {
      const detalhesProduto = document.getElementById("detalhes-produto");
     
      navegar('tela-produto')
      detalhesProduto.innerHTML = "Carregando..."
      const response = await axios.get(`http://localhost:3000/products/${id}`)
      const p = response.data;




      detalhesProduto.innerHTML = `
      <div class="row g-3">
          <div class="col-md-4 text-center">
          <img src="${p.image}" class="img-fluid" alt="${p.title}">
          </div>
          <div class="col-md-8">
          <h2>${p.title}</h2>
          <p><strong>Categoria:</strong> ${p.category}</p>
          <p><strong>Preço:</strong> R$ ${p.price}</p>
          <p><strong>Descrição:</strong> ${p.description}</p>
          <p><strong>Avaliação:</strong> ${p.rating.rate} ⭐ (${p.rating.count} avaliações)</p>
          </div>
      </div>`


     
  } catch (error) {
      console.error("Erro ao carregar detalhes:", error)
  }
}

async function cadastrarProduto(){
    const cadastroProduto = document.getElementById("cadastro-produto");
    const botaoCadastro = document.getElementById("botao-cadastro");

    botaoCadastro.classList.remove("show")
    botaoCadastro.classList.add("collapse")

    navegar('tela-cadastro')
    cadastroProduto.innerHTML = "Carregando..."
    //const response = await axios.get(`http://localhost:3000/products/${id}`)
    //const p = response.data;

    cadastroProduto.innerHTML = `
      <div class="row g-3">
          <div class="col-12 col-md-10 col-lg-12">
            <form action="" method="" class="form">
              <fieldset class="grid-form">
                <legend>Cadastro de Produto</legend>

                <label for="nome">Nome:</label>
                <input type="text" name="nome" id="nome">

                <label for="categoria">Categoria:</label>
                <select name="categoria" id="categoria">

                  <option value="men&#39;s clothing">Vestuário</option>
                  <option value="electronics">Eletrônicos</option>
                  <option value="jewelery">Acessórios</option>
                </select>

                <label for="preco">Preço:</label>
                <input type="number" name="preco" id="preco" min="0" max="10000" step="0.01">

                <label for="descricao">Descrição:</label>
                <input type="text" name="descricao" id="descricao">

                <label for="imagem">Tipoz:</label>
                <input type="hidden" name="imgUrl" id="imgUrl">

                <div class="imagens-form">
                  <img class="imagem-form" id="imagem" src="https://png.pngtree.com/png-vector/20241203/ourmid/pngtree-white-t-shirt-mockup-png-image_14585412.png" onclick="setImage('https://png.pngtree.com/png-vector/20241203/ourmid/pngtree-white-t-shirt-mockup-png-image_14585412.png'); setBorda(this)">
                  <img class="imagem-form" id="imagem" src="https://cdn.prod.website-files.com/60c368fa646716418bdf9ce9/614b4884362e9151e5e4a4b5_189142-conheca-as-principais-caracteristicas-das-joias-de-ouro-amarelo-p-1080.jpeg" onclick="setImage('https://cdn.prod.website-files.com/60c368fa646716418bdf9ce9/614b4884362e9151e5e4a4b5_189142-conheca-as-principais-caracteristicas-das-joias-de-ouro-amarelo-p-1080.jpeg'); setBorda(this)">
                  <img class="imagem-form" id="imagem" src="https://s2-techtudo.glbimg.com/c_kPjuL88epOUepOndI324c3jlw=/0x0:1280x720/1000x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2025/u/t/D0Awd4S6eqg2YyaAOOog/6.png" onclick="setImage('https://s2-techtudo.glbimg.com/c_kPjuL88epOUepOndI324c3jlw=/0x0:1280x720/1000x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2025/u/t/D0Awd4S6eqg2YyaAOOog/6.png'); setBorda(this)">
                </div>

              </fieldset>
            <button type="submit" class="button1">Cadastrar Produto</button>
          </form>
          </div>
      </div>`
      const form = document.querySelector('.form');
  
  form.addEventListener('submit', async function(e) {
    e.preventDefault()

    let nomeProduto = document.getElementById('nome').value;
    let categoriaProduto = document.getElementById('categoria').value;
    let precoProduto = document.getElementById('preco').value;
    let precoOficial = Number(precoProduto.replace(',', '.')) || 0;
    let descricaoProduto = document.getElementById('descricao').value;
    let imagemProduto = document.getElementById('imgUrl').value;

    let precoProdutoFormatado = precoProduto

    let dadosNovos = {
      "title": nomeProduto,
      "price": precoOficial.toFixed(2),
      "description": descricaoProduto,
      "category": categoriaProduto,
      "image": imagemProduto,
      "rating": {
        "rate": 0,
        "count": 0
      }
    }

    try{
      const result = await axios.post('http://localhost:3000/products', dadosNovos)
      console.log(result)
    } catch(e){
      console.log(error)
    }
  })

}

function setImage(url){
  try{
    const urlModificada = new URL(url);
    let urlDestino = urlModificada.searchParams.get('urlDestino') || 
                    urlModificada.searchParams.get('urldestino');

    let urlFinal = urlDestino ? decodeURIComponent(urlDestino):url;

    document.getElementById('imgUrl').value = urlFinal;
  } catch(erro){
    document.getElementById('imgUrl').value = url;
  }
}

function setBorda(imagemSelecionada){
  const imagemAnterior = document.querySelector('.imagem-form.selecionado');
      if(imagemAnterior){
        imagemAnterior.classList.remove('selecionado')
      }
  imagemSelecionada.classList.add('selecionado')
}
