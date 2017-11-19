package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

public enum Sexo {

    F("Feminino"), M("Masculino");
    public static Sexo FEMININO = F;
    public static Sexo MASCULINO = M;
    private String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricao() {
        return sexo;
    }

}
