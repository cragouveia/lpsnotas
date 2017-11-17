package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Professor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfessorBuilder {

    private static SimpleDateFormat strToDate = new SimpleDateFormat("dd/MM/yyyy");
    private List<Professor> professors = new ArrayList();

    private ProfessorBuilder(Professor professor) {
        professors.add(professor);
    }

    public static ProfessorBuilder newProfessor(String nome, String cpf, String rg, String senha, String rga, String dataNascimento, String telefone, String email, String nacionalidade,
                                                  String cidade, String uf, String dataAdmissao, double salario, String sexo, Funcao funcao, String faculdade) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            Calendar calendarDataAdmissao = Calendar.getInstance();
            calendarDataAdmissao.setTime(strToDate.parse(dataAdmissao));
            Professor professor = create(nome, cpf, rg, senha, rga, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, calendarDataAdmissao, salario, sexo, funcao, faculdade);
            return new ProfessorBuilder(professor);
        }
        catch (Exception e) {
            return null;
        }
    }

    private static Professor create(String nome, String cpf, String rg, String senha, String rga, Calendar dataNascimento, String telefone, String email, String nacionalidade,
                                      String cidade, String uf, Calendar dataAdmissao, double salario, String sexo, Funcao funcao, String faculdade) {
        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setCpf(cpf);
        professor.setRg(rg);
        professor.setSenha(senha);
        professor.setRga(rga);
        professor.setDataNascimento(dataNascimento);
        professor.setTelefone(telefone);
        professor.setEmail(email);
        professor.setNacionalidade(nacionalidade);
        professor.setCidade(cidade);
        professor.setUf(uf);
        professor.setDataAdmissao(dataAdmissao);
        professor.setSalario(salario);
        professor.setSexo(sexo);
        professor.setFuncao(funcao);
        professor.setFaculdade(faculdade);
        return professor;
    }

    public ProfessorBuilder more(String nome, String cpf, String rg, String senha, String rga, String dataNascimento, String telefone, String email, String nacionalidade,
                                                String cidade, String uf, String dataAdmissao, double salario, String sexo, Funcao funcao, String faculdade) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            Calendar calendarDataAdmissao = Calendar.getInstance();
            calendarDataAdmissao.setTime(strToDate.parse(dataAdmissao));
            professors.add(create(nome, cpf, rg, senha, rga, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, calendarDataAdmissao, salario, sexo, funcao, faculdade));
            return this;
        }
        catch (Exception e) {
            return null;
        }
    }

    public Professor buildOne() { return professors.get(0);}

    public List<Professor> buildAll() {
        return professors;
    }


}
