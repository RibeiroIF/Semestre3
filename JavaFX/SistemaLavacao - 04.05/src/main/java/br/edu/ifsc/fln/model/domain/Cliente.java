package br.edu.ifsc.fln.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public abstract class Cliente {
    protected int id;
    protected String nome, celular, email;
    protected LocalDate dataCadastro;
    protected List<Veiculo> ListaDeVeiculos = new ArrayList<Veiculo>();
    protected Pontuacao pontuacao;

    public Cliente() {
        super();
    }

    public Cliente(int id, String nome, String celular, String email, LocalDate dataCadastro) {
        super();
        this.id = id;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.pontuacao = new Pontuacao();
    }

    public List<Veiculo> getListaDeVeiculos(){
        return ListaDeVeiculos;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        ListaDeVeiculos.add(veiculo);
        veiculo.setCliente(this);
    }

//    // EXCEÇÃO AO REMOVER UM VEÍCULO QUE NÃO PERTENÇA AO CLIENTE
//    public void removerVeiculo(Veiculo veiculo) throws ExceptionLavacao {
//        if (!this.ListaDeVeiculos.contains(veiculo)) {
//            throw new ExceptionLavacao("Não é possível retirar este carro pois não pertence ao cliente");
//        }
//        else {
//            ListaDeVeiculos.remove(veiculo);
//            veiculo.setCliente(null);
//        }
//    }

    public Pontuacao getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
