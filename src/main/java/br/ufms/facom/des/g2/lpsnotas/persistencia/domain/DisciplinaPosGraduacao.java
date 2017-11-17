package br.ufms.facom.des.g2.lpsnotas.persistencia.domain;

import java.util.Calendar;

public class DisciplinaPosGraduacao extends TipoDisciplina {

    private Calendar dataCriacao;

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
