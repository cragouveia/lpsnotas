package br.ufms.facom.des.g2.lpsnotas.interfaces;

import br.ufms.facom.des.g2.lpsnotas.negocio.FuncaoService;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;

import java.util.List;

public class LpsNotasApplication {
    public static void main(String[] args) {
        FuncaoService funcaoService = new FuncaoService();
        try {
            funcaoService.start();
            List<Funcao> funcoes = funcaoService.getAll();
            funcoes.forEach(f -> System.out.println(f.exibir()));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
