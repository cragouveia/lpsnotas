package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

import java.util.Calendar;

public class Turma implements Entidade{

    private long codigo;
    private String sigla;
    private Calendar dataInclusao;
    private Calendar dataExclusao;
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

    public Calendar getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Calendar dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Calendar getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(Calendar dataExclusao) {
        this.dataExclusao = dataExclusao;
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
}
