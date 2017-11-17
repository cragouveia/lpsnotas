package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

import java.util.Calendar;

public class Disciplina {

    private long codigo;
    private String nome;
    private String sigla;
    private String faculdadeResponsavel;
    private Calendar dataInclusao;
    private Calendar dataExclusao;
    private String ementa;

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getFaculdadeResponsavel() {
        return faculdadeResponsavel;
    }

    public void setFaculdadeResponsavel(String faculdadeResponsavel) {
        this.faculdadeResponsavel = faculdadeResponsavel;
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

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

}
