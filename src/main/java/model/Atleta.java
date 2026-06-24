package model;

public class Atleta {
    private int id;
    private String nome;
    private int idade;
    private String faixa;
    private double peso;

    public Atleta() {}

    public Atleta(int id, String nome, int idade, String faixa, double peso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.faixa = faixa;
        this.peso = peso;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getFaixa() { return faixa; }
    public double getPeso() { return peso; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setFaixa(String faixa) { this.faixa = faixa; }
    public void setPeso(double peso) { this.peso = peso; }
}