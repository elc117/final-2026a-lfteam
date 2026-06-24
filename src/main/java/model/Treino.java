package model;

public class Treino {
    private Long id;
    private String data;
    private String descricao;

    public Treino() {}

    public Treino(Long id, String data, String descricao) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public String getData() { return data; }
    public String getDescricao() { return descricao; }

    public void setId(Long id) { this.id = id; }
    public void setData(String data) { this.data = data; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}