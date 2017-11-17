package br.ufms.facom.des.g2.lpsnotas.interfaces;

import br.ufms.facom.des.g2.lpsnotas.negocio.*;

public class LpsNotasApplication {
    public static void main(String[] args) {
        FuncaoService funcaoService = new FuncaoService();
        FuncionarioService funcionarioService = new FuncionarioService();
        ProfessorService professorService = new ProfessorService();
        SalaService salaService = new SalaService();
        try {
            funcaoService.getAll().forEach(o -> System.out.println(o.exibir()));
            System.out.println();
            funcionarioService.getAll().forEach(o -> System.out.println(o.exibir()));
            System.out.println();
            professorService.getAll().forEach(o -> System.out.println(o.exibir()));
            System.out.println();
            salaService.getAll().forEach(o -> System.out.println(o.exibir()));
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
