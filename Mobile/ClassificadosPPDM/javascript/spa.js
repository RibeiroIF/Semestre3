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


   try {
       let url;
       const telaLista = document.getElementById("tela-home");


       // Define URLs conforme categoria selecionada
       if (categoria === 'todos') {
           url = 'https://fakestoreapi.com/products';
       } else {
           url = `https://fakestoreapi.com/products/category/${categoria}`;
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
               <img src="${produto.image.replace(".jpg", "t.png")}" class="card-img-top p-3" style="height:250px; object-fit:contain;">
               <div class="card-body">
                   <h5 class="card-title">${produto.title}</h5>
                   <p class="card-text">R$ ${produto.price.toFixed(2)}</p>
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
      const response = await axios.get(`https://fakestoreapi.com/products/${id}`)
      const p = response.data;




      detalhesProduto.innerHTML = `
      <div class="row g-3">
          <div class="col-md-4 text-center">
          <img src="${p.image}" class="img-fluid" alt="${p.title}">
          </div>
          <div class="col-md-8">
          <h2>${p.title}</h2>
          <p><strong>Categoria:</strong> ${p.category}</p>
          <p><strong>Preço:</strong> R$ ${p.price.toFixed(2)}</p>
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
    //const response = await axios.get(`https://fakestoreapi.com/products/${id}`)
    //const p = response.data;

    cadastroProduto.innerHTML = `
      <div class="row g-3">
          <div class="col-md-8">
            <form class="form">
              <fieldset>
                <legend>Cadastro de Produto</legend>
                <label for="title">Nome do Produto:</label>
                <input type="text" name="title" id="title"><br>
                <label for="category">Categoria do Produto:</label>
                <select name="category" id="category">
                  <option value="men\s' clothing">Vestuário</option>
                  <option value="electronics">Eletrônicos</option>
                  <option value="jewelery">Acessórios</option>
                </select> <br>
                <label for="price">Preço do Produto:</label>
                <input type="number" name="price" id="price" min="0" max="10000"><br>
                <label for="description">Descrição:</label>
                <input type="text" name="description" id="description"> <br>
                <label for="image">Foto do produto:</label>
                <input type="file" name="image" accept="image/*" id="imagem">
              </fieldset>
              <button type="submit" class="button1">Cadastrar Produto</button>
            </form>
          </div>
      </div>`
      const form = document.querySelector('.form');
  
  form.addEventListener('submit', async function(e) {
    e.preventDefault()

    const formData = new FormData(form);

    console.log([...formData])

    try{
      const result = await axios.post('https://fakestoreapi.com/products', formData)
      console.log(result)
    } catch(e){
      console.log(error)
    }
  })

}
