<?php

class Livro{
   private $titulo;
   private $autor;
   private $isbn;
   private $precoVenda;
   private $percentualDesconto;
   
   public function __construct(){
      $this->titulo = $_POST["titulo"];
      $this->autor = $_POST["autor"];
      $this->isbn = $_POST["isbn"];
      $this->precoVenda = $_POST["precoVenda"];
      $this->percentualDesconto = $_POST["percentualDesconto"];
   }

   public function aplicarDesconto(){
      $this->precoVenda -= $this->precoVenda * ($this->percentualDesconto/100);
   }

   public function mostrarInfos(){
      echo "<h3> Aqui estão todas as informações do livro </h3>
      <ul>
      <li> Título = <span> $this->titulo </span> </li>
      <li> Autor = <span> $this->autor </span> </li>
      <li> ISBN = <span> $this->isbn </span> </li>
      <li> Preço original = R$<span> $this->precoVenda </span> </li>
      </ul>";
   }

   public function getTitulo(){
      return $this->titulo;
   }
   public function getAutor(){
      return $this->autor;
   }
   public function getIsbn(){
      return $this->isbn;
   }
   public function getPrecoVenda(){
      return $this->precoVenda;
   }

}
