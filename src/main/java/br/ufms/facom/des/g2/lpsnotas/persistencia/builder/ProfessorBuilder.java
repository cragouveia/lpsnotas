package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Professor;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sexo;

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

    public static ProfessorBuilder newProfessor(String nome, String cpf, String rg, String dataNascimento, String telefone, String email, String nacionalidade,
                                                  String cidade, String uf, double salario, String sexo, String cargo, Funcao funcao, String faculdade) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            Professor professor = create(nome, cpf, rg, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, salario, sexo, cargo, funcao, faculdade);
            return new ProfessorBuilder(professor);
        }
        catch (Exception e) {
            return null;
        }
    }

    private static Professor create(String nome, String cpf, String rg, Calendar dataNascimento, String telefone, String email, String nacionalidade,
                                      String cidade, String uf, double salario, String sexo, String cargo, Funcao funcao, String faculdade) {
        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setCpf(cpf);
        professor.setRg(rg);
        professor.setDataNascimento(dataNascimento);
        professor.setTelefone(telefone);
        professor.setEmail(email);
        professor.setNacionalidade(nacionalidade);
        professor.setCidade(cidade);
        professor.setUf(uf);
        professor.setSalario(salario);
        professor.setSexo(Sexo.valueOf(sexo));
        professor.setCargo(cargo);
        professor.setFuncao(funcao);
        professor.setFaculdade(faculdade);
        return professor;
    }

    public ProfessorBuilder more(String nome, String cpf, String rg, String dataNascimento, String telefone, String email, String nacionalidade,
                                                String cidade, String uf, double salario, String sexo, String cargo, Funcao funcao, String faculdade) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            professors.add(create(nome, cpf, rg, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, salario, sexo, cargo, funcao, faculdade));
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
