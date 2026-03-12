package ifsc.atividade1.model.domain;

public class Pessoa {

    private String nome;
    private int idade;
    private String sexo;
    private double altura;
    private double peso;

    public Pessoa() {
    }

    public Pessoa(String nome, int idade, String sexo, double altura, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double calcularIMC(){
        return this.getPeso()/(this.getAltura()*this.getAltura());
    }

    public String verClassificacao(){
        if (this.calcularIMC() < 18.5){
            return "Baixo peso";
        }
        else if (this.calcularIMC() > 18.5 && this.calcularIMC() < 24.9){
            return "Eutrofia (peso adequado)";
        }
        else if (this.calcularIMC() > 25 && this.calcularIMC() < 29.9){
            return "Sobrepeso";
        }
        else if (this.calcularIMC() > 30 &&  this.calcularIMC() < 34.9){
            return "Obesidade grau 1";
        }
        else if (this.calcularIMC() > 35 && this.calcularIMC() < 39.9){
            return "Obesidade grau 2";
        }
        else if (this.calcularIMC() > 40) {
            return "Obesidade grau 3";
        }
        else{
            return "Fora do alcance da classificação";
        }
    }

    public String mostrarDados(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cálculo de IMC").append("\n\n");
        sb.append("Nome: ").append(this.getNome()).append("\n");
        sb.append("Idade: ").append(this.getIdade()).append("\n");
        sb.append("Sexo: ").append(this.getSexo()).append("\n");
        sb.append("Altura: ").append(this.getAltura()).append("\n");
        sb.append("Peso: ").append(this.getPeso()).append("\n\n");
        sb.append("IMC: ").append(this.calcularIMC()).append("\n");
        sb.append("Classificação: ").append(this.verClassificacao()).append("\n");
        return sb.toString();
    }
}
