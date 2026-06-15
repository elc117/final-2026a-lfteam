package model;

public class Treino {

    private Long id;
    private String data;
    private String descricao;

    public Treino(Long id,
                  String data,
                  String descricao) {

        this.id = id;
        this.data = data;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }
}