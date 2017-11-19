package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina.PersistenciaDisciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;

public class GerenciarDisciplina<T> extends Gerenciar<Disciplina>{


    private PersistenciaDisciplina<T> persistenciaDisciplina;

    public GerenciarDisciplina(PersistenciaDisciplina<T> persistenciaDisciplina) {
        super(persistenciaDisciplina.getDao());
        this.persistenciaDisciplina = persistenciaDisciplina;
    }
}
