package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaGraduacao;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaGraduacaoBuilder {

    private List<DisciplinaGraduacao> disciplinas = new ArrayList();

    private DisciplinaGraduacaoBuilder(DisciplinaGraduacao disciplinaGraduacao) {
        disciplinas.add(disciplinaGraduacao);
    }

    public static DisciplinaGraduacaoBuilder newDisciplinaGraduacao(String nome, String sigla, String ementa, String eixo) {
        DisciplinaGraduacao disciplinaGraduacao = create(nome, sigla, ementa, eixo);
        return new DisciplinaGraduacaoBuilder(disciplinaGraduacao);
    }

    private static DisciplinaGraduacao create(String nome, String sigla, String ementa, String eixo) {
        DisciplinaGraduacao disciplinaGraduacao = new DisciplinaGraduacao();
        disciplinaGraduacao.setNome(nome);
        disciplinaGraduacao.setSigla(sigla);
        disciplinaGraduacao.setEmenta(ementa);
        disciplinaGraduacao.setEixo(eixo);
        return disciplinaGraduacao;
    }

    public DisciplinaGraduacaoBuilder more(String nome, String sigla, String ementa, String eixo) {
        disciplinas.add(create(nome, sigla, ementa, eixo));
        return this;
    }

    public DisciplinaGraduacao buildOne() { return disciplinas.get(0);}

    public List<DisciplinaGraduacao> buildAll() {
        return disciplinas;
    }


}
