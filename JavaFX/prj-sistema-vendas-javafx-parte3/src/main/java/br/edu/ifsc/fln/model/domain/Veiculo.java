package br.edu.ifsc.fln.model.domain;

public class Veiculo {
    private int id;
    private String placa, observacoes;

    private Cor cor;
    private Modelo modelo;
    private Cliente cliente;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String observacoes, Cor cor, Modelo modelo) {
        this.id = id;
        this.placa = placa;
        this.observacoes = observacoes;
        this.cor = cor;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
