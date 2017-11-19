package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public enum Status {

    A("Aprovado"), R("Reprovado");
    public static Status APROVADO = A;
    public static Status REPROVADO = R;
    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return status;
    }

}
