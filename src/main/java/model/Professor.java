package model;

public class Professor {
    private Long id;
    private String nome;
    private String graduacao;

    public Professor() {}

    public Professor(Long id, String nome, String graduacao) {
        this.id = id;
        this.nome = nome;
        this.graduacao = graduacao;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getGraduacao() { return graduacao; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setGraduacao(String graduacao) { this.graduacao = graduacao; }
}