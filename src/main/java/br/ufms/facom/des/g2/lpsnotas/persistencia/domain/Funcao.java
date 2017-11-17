package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public class Funcao implements Entidade {

    private long codigo;
    private String nome;
    private String descricao;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String exibir() {
        return String.format("Função %d - %s", this.getCodigo(), this.getNome());
    }

}
