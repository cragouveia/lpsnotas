package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public class Professor extends Funcionario {

    private String faculdade;

    public Professor() {
    }

    public String getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }

    @Override
    public String exibir() {
        return String.format("Professor %d - %s - %s", this.getCodigo(), this.getCpf(), this.getNome());
    }


}
