package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.TipoDisciplina;

import java.util.ArrayList;
import java.util.List;

public class TipoDisciplinaBuilder {

    private List<TipoDisciplina> tipos = new ArrayList();

    private TipoDisciplinaBuilder(TipoDisciplina tipoDisciplina) {
        tipos.add(tipoDisciplina);
    }

    public static TipoDisciplinaBuilder newTipoDisciplina(String descricao) {
        TipoDisciplina tipoDisciplina = create(descricao);
        return new TipoDisciplinaBuilder(tipoDisciplina);
    }

    private static TipoDisciplina create(String descricao) {
        TipoDisciplina tipoDisciplina = new TipoDisciplina();
        tipoDisciplina.setDescricao(descricao);
        return tipoDisciplina;
    }

    public TipoDisciplinaBuilder more(String descricao) {
        tipos.add(create(descricao));
        return this;
    }

    public TipoDisciplina buildOne() { return tipos.get(0);}

    public List<TipoDisciplina> buildAll() {
        return tipos;
    }


}
