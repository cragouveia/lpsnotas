package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Avaliacao;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoBuilder {

    private List<Avaliacao> avaliacoes = new ArrayList();

    private AvaliacaoBuilder(Avaliacao funcao) {
        avaliacoes.add(funcao);
    }

    public static AvaliacaoBuilder newAvaliacao(String nome) {
        Avaliacao funcao = create(nome);
        return new AvaliacaoBuilder(funcao);
    }

    private static Avaliacao create(String nome) {
        Avaliacao funcao = new Avaliacao();
        funcao.setNome(nome);
        return funcao;
    }

    public AvaliacaoBuilder more(String nome) {
        avaliacoes.add(create(nome));
        return this;
    }

    public Avaliacao buildOne() { return avaliacoes.get(0);}

    public List<Avaliacao> buildAll() {
        return avaliacoes;
    }


}
