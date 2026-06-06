<?php

class Carro{
   
   public $fabricante;
   public $modelo;
   public $precoVenda;
   public $classificacaoCarro = "";

   public function __construct(){
      $this->fabricante = $_POST['fabricante'];
      $this->modelo = $_POST['modelo'];
      $this->precoVenda = $_POST['precoVenda'];
   }

   public function classificarCarro(){
      if ($this->precoVenda < 100000){
         return "Popular";
      }
      elseif ($this->precoVenda >= 100000 && $this->precoVenda <= 300000){
         return "Performance Média";
      }
      else{
         return "Alta Performance";
      }
   }

   public function exibirInfos(){
      if ($this->classificarCarro() == "Popular"){
         echo "<h3> Aqui estão todas as informações do livro </h3>
         <ul>
            <li> Fabricante = <span> $this->fabricante </span> </li>
            <li> Modelo = <span> $this->modelo </span> </li>
            <li> Preço de venda = R$<span> $this->precoVenda </span> </li>
         </ul>";
      }
      else{
         echo "<h3> O carro não é Popular, portanto não serão mostradas as informações referentes a ele </h3>";
      }
   }
}