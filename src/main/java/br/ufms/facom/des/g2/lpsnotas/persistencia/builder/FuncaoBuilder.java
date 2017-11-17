package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;

import java.util.ArrayList;
import java.util.List;

public class FuncaoBuilder {

    private List<Funcao> funcoes = new ArrayList();

    private FuncaoBuilder(Funcao funcao) {
        funcoes.add(funcao);
    }

    public static FuncaoBuilder newFuncao(String nome, String descricao) {
        Funcao funcao = create(nome, descricao);
        return new FuncaoBuilder(funcao);
    }

    private static Funcao create(String nome, String descricao) {
        Funcao funcao = new Funcao();
        funcao.setNome(nome);
        funcao.setDescricao(descricao);
        return funcao;
    }

    public  FuncaoBuilder more(String nome, String descricao) {
        funcoes.add(create(nome, descricao));
        return this;
    }
    public Funcao buildOne() { return funcoes.get(0);}

    public List<Funcao> buildAll() {
        return funcoes;
    }


}
