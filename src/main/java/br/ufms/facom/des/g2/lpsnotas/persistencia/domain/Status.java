package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public enum Status {

    A("Aprovado"), R("Reprovado"), E("Em andamento");
    public static Status APROVADO = A;
    public static Status REPROVADO = R;
    public static Status EM_ANDAMENTO = E;
    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return status;
    }

}
