package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public class Avaliacao implements Entidade{

    private long codigo;
    private String nome;
    private Turma turma;

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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String exibir() {
        return String.format("Avaliação: %d - %s => Turma %s", this.getCodigo(), this.getNome(), this.getTurma().getSigla());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avaliacao avaliacao = (Avaliacao) o;

        return codigo == avaliacao.codigo;
    }

    @Override
    public int hashCode() {
        return (int) (codigo ^ (codigo >>> 32));
    }
}
