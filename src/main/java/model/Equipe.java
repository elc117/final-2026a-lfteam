package model;

public class Equipe {

    private Long id;
    private String nome;
    private String cidade;

    public Equipe(Long id, String nome, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }
}