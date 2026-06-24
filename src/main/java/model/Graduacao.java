package model;

public class Graduacao {
    private Long id;
    private String faixaAnterior;
    private String faixaNova;
    private String dataGraduacao;

    public Graduacao() {}

    public Graduacao(Long id, String faixaAnterior, String faixaNova, String dataGraduacao) {
        this.id = id;
        this.faixaAnterior = faixaAnterior;
        this.faixaNova = faixaNova;
        this.dataGraduacao = dataGraduacao;
    }

    public Long getId() { return id; }
    public String getFaixaAnterior() { return faixaAnterior; }
    public String getFaixaNova() { return faixaNova; }
    public String getDataGraduacao() { return dataGraduacao; }

    public void setId(Long id) { this.id = id; }
    public void setFaixaAnterior(String faixaAnterior) { this.faixaAnterior = faixaAnterior; }
    public void setFaixaNova(String faixaNova) { this.faixaNova = faixaNova; }
    public void setDataGraduacao(String dataGraduacao) { this.dataGraduacao = dataGraduacao; }
}