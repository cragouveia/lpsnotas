package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaPosGraduacao;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaPosGraduacaoBuilder {

    private List<DisciplinaPosGraduacao> disciplinas = new ArrayList();

    private DisciplinaPosGraduacaoBuilder(DisciplinaPosGraduacao disciplinaPosGraduacao) {
        disciplinas.add(disciplinaPosGraduacao);
    }

    public static DisciplinaPosGraduacaoBuilder newDisciplinaPosGraduacao(String nome, String sigla, String ementa, String area) {
        DisciplinaPosGraduacao disciplinaPosGraduacao = create(nome, sigla, ementa, area);
        return new DisciplinaPosGraduacaoBuilder(disciplinaPosGraduacao);
    }

    private static DisciplinaPosGraduacao create(String nome, String sigla, String ementa, String area) {
        DisciplinaPosGraduacao disciplinaPosGraduacao = new DisciplinaPosGraduacao();
        disciplinaPosGraduacao.setNome(nome);
        disciplinaPosGraduacao.setSigla(sigla);
        disciplinaPosGraduacao.setEmenta(ementa);
        disciplinaPosGraduacao.setArea(area);
        return disciplinaPosGraduacao;
    }

    public DisciplinaPosGraduacaoBuilder more(String nome, String sigla, String ementa, String area) {
        disciplinas.add(create(nome, sigla, ementa, area));
        return this;
    }

    public DisciplinaPosGraduacao buildOne() { return disciplinas.get(0);}

    public List<DisciplinaPosGraduacao> buildAll() {
        return disciplinas;
    }


}
