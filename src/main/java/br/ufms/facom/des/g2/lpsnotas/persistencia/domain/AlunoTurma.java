package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

import java.util.Calendar;

public class AlunoTurma implements Entidade {

    private long codigo;
    private Aluno aluno;
    private Turma turma;
    private Calendar dataInicio;
    private Calendar dataTermino;

    @Override
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
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

    @Override
    public String exibir() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlunoTurma that = (AlunoTurma) o;

        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return (int) (codigo ^ (codigo >>> 32));
    }
}
