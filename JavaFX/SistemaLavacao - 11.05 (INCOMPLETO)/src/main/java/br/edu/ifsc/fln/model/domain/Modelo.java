package br.edu.ifsc.fln.model.domain;

public class Modelo {
    private int id;
    private String descricao;

    private Motor motor;
    private ECategoria categoria;
    private Marca marca;

    public Modelo() {
    }

    public Modelo(int id, String descricao, Marca marca, ECategoria categoria, int potencia,
                  ETipoCombustivel combustivel) {
        this.id = id;
        this.descricao = descricao;
        this.motor = new Motor(potencia, combustivel);
        this.categoria = categoria;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ECategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ECategoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

}
