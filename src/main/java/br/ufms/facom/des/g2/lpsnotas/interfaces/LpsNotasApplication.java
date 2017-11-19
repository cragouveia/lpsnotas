package br.ufms.facom.des.g2.lpsnotas.interfaces;

import br.ufms.facom.des.g2.lpsnotas.negocio.GerenciarDisciplina;
import br.ufms.facom.des.g2.lpsnotas.negocio.GerenciarFuncao;
import br.ufms.facom.des.g2.lpsnotas.negocio.GerenciarFuncionario;
import br.ufms.facom.des.g2.lpsnotas.negocio.GerenciarProfessor;
import br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina.PersistenciaDisciplinaGraduacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.Database;
import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.DisciplinaGraduacaoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.DisciplinaPosGraduacaoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaGraduacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaPosGraduacao;

public class LpsNotasApplication {
    public static void main(String[] args) {
        try {
            Database.setProperties("root", "mysql", "lpsxx");
            GerenciarFuncao gerenciarFuncao = new GerenciarFuncao();
            gerenciarFuncao.start();
            gerenciarFuncao.getAll().forEach(funcao -> System.out.println(funcao.exibir()));
            //
            GerenciarFuncionario gerenciarFuncionario = new GerenciarFuncionario();
            gerenciarFuncionario.start();
            gerenciarFuncionario.getAll().forEach(funcao -> System.out.println(funcao.exibir()));
            //
            GerenciarProfessor gerenciarProfessor = new GerenciarProfessor();
            gerenciarProfessor.start();
            gerenciarProfessor.getAll().forEach(funcao -> System.out.println(funcao.exibir()));
            //
            GerenciarDisciplina gerenciarDisciplina = new GerenciarDisciplina(new PersistenciaDisciplinaGraduacao());
            DisciplinaGraduacao d = DisciplinaGraduacaoBuilder.newDisciplinaGraduacao("Desenvolvimento de Sistemas", "DEV", "Desenvolvimento de Sistemas", "Eixo Primário").buildOne();
            gerenciarDisciplina.save(d);
            DisciplinaPosGraduacao d1 = DisciplinaPosGraduacaoBuilder.newDisciplinaPosGraduacao("Desenvolvimento de Sistemas 2", "DEV2", "Desenvolvimento de Sistemas2", "Area 1").buildOne();
            gerenciarDisciplina.save(d1);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
