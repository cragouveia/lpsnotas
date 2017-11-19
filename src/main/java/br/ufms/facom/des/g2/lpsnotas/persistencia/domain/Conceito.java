package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public enum Conceito {

    A("Excelente"), B("Bom"), C("Regular"), D("Insuficiente");
    public static Conceito EXCELENTE = A;
    public static Conceito BOM = B;
    public static Conceito REGULAR = C;
    public static Conceito INSUFICIENTE = D;
    private String conceito;

    Conceito(String conceito) {
        this.conceito = conceito;
    }

    public String getDescricao() {
        return conceito;
    }

    public int getEquivalencia() {
        int value = 0;
        switch (conceito) {
            case "A":
                value =  3;
                break;

            case "B":
                value =  2;
                break;
            case "C":
                value = 1;
                break;
            case "D":
                value =  0;
                break;
        }
        return value;
    }

}
