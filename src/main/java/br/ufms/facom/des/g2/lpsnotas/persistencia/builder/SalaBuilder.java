package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaBuilder {

    private List<Sala> salas = new ArrayList();

    private SalaBuilder(Sala sala) {
        salas.add(sala);
    }

    public static SalaBuilder newSala(String nome, String descricao, String bloco, int capacidade) {
        Sala sala = create(nome, descricao, bloco, capacidade);
        return new SalaBuilder(sala);
    }

    private static Sala create(String nome, String descricao, String bloco, int capacidade) {
        Sala sala = new Sala();
        sala.setNome(nome);
        sala.setDescricao(descricao);
        sala.setBloco(bloco);
        sala.setCapacidade(capacidade);
        return sala;
    }

    public SalaBuilder more(String nome, String descricao, String bloco, int capacidade) {
        salas.add(create(nome, descricao, bloco, capacidade));
        return this;
    }

    public Sala buildOne() { return salas.get(0);}

    public List<Sala> buildAll() {
        return salas;
    }


}
