package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public class NotaAvaliacao {

    private long codigo;
    private Avaliacao avaliacao;
    private AndamentoIndividual andamentoIndividual;
    private double nota;
    private Conceito conceito;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public AndamentoIndividual getAndamentoIndividual() {
        return andamentoIndividual;
    }

    public void setAndamentoIndividual(AndamentoIndividual andamentoIndividual) {
        this.andamentoIndividual = andamentoIndividual;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Conceito getConceito() {
        return conceito;
    }

    public void setConceito(Conceito conceito) {
        this.conceito = conceito;
    }

    public int getConceitoNota() {
        return this.getConceito().getEquivalencia();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotaAvaliacao that = (NotaAvaliacao) o;

        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return (int) (codigo ^ (codigo >>> 32));
    }
}
