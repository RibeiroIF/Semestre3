<?php

class Hardware{

   public $nome;
   public $classificacao;
   public $precoVenda;
   public $desconto;

   public function __construct(){
      $this->nome = $_POST['nome'];
      $this->classificacao = $_POST['classificacao'];
      $this->precoVenda = $_POST['precoVenda'];
   }

   public function calcularDesconto(){
      if ($this->classificacao == "hardware"){
         $this->desconto = $this->precoVenda * 0.05;
      }
      else{
         $this->desconto = $this->precoVenda * 0.07;
      }
      $this->precoVenda = $this->precoVenda - $this->desconto;
   }

   public function mostrarInfos(){
      echo "<h3> Aqui estão todas as informações do item de informática </h3>
      <ul>
      <li> Nome do item = <span> $this->nome </span> </li>
      <li> Classificação = <span> $this->classificacao </span> </li>
      <li> Percentual de desconto = <span> $this->precoVenda %</span> </li>
      <li> Preço de venda final = R$<span> $this->desconto </span> </li>
      </ul>";
   }
   
}