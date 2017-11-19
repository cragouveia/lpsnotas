package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

import java.util.Calendar;

public class Turma implements Entidade {

    private long codigo;
    private String sigla;
    private Calendar dataInicio;
    private Calendar dataTermino;
    private int qtdeAvaliacao;
    private Sala sala;
    private Disciplina disciplina;
    private Professor professor;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Calendar dataTermino) {
        this.dataTermino = dataTermino;
    }

    public int getQtdeAvaliacao() {
        return qtdeAvaliacao;
    }

    public void setQtdeAvaliacao(int qtdeAvaliacao) {
        this.qtdeAvaliacao = qtdeAvaliacao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String exibir() {
        return String.format("Turma: %d - %s", this.getCodigo(), this.getSigla());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Turma turma = (Turma) o;

        return codigo == turma.codigo;
    }

    @Override
    public int hashCode() {
        return (int) (codigo ^ (codigo >>> 32));
    }
}
