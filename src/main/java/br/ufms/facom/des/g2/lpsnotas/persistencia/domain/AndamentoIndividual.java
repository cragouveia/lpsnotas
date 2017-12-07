package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

import java.util.ArrayList;
import java.util.List;

public class AndamentoIndividual implements Entidade {

    private long codigo;
    private double mediaFinal;
    private Status status;
    private Aluno aluno;
    private Turma turma;
    private Professor professor;
    private List<NotaAvaliacao> notas = new ArrayList();

    public AndamentoIndividual() {
        status = Status.EM_ANDAMENTO;
    }

    @Override
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<NotaAvaliacao> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaAvaliacao> notas) {
        this.notas = notas;
    }

    public void addNota(NotaAvaliacao nota) {
        notas.add(nota);
    }

    public void removeNota(NotaAvaliacao nota) {
        notas.remove(nota);
    }

    @Override
    public String exibir() {
        return String.format("Andamento: Aluno %s - Média %2.f - Status: %s", this.getAluno().getNome(), this.getMediaFinal(), this.getStatus().getDescricao());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AndamentoIndividual that = (AndamentoIndividual) o;

        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return (int) (codigo ^ (codigo >>> 32));
    }
}
