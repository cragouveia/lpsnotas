package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public class TipoDisciplina implements Entidade{

    private long codigo;
    private String descricao;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String exibir() {
        return String.format("Tipo de Disciplina %l - %s", this.getCodigo(), this.getDescricao());
    }

}
