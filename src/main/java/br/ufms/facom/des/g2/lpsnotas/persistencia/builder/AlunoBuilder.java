package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Aluno;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sexo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlunoBuilder {

    private static SimpleDateFormat strToDate = new SimpleDateFormat("dd/MM/yyyy");
    private List<Aluno> alunos = new ArrayList();

    public AlunoBuilder(Aluno aluno) {
        alunos.add(aluno);
    }

    public static AlunoBuilder newAluno(String nome, String cpf, String rg, String dataNascimento, String telefone, String email, String nacionalidade,
                                        String cidade, String uf, String rga, String sexo, String curso) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            Aluno aluno = create(nome, cpf, rg, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, rga, sexo, curso);
            return new AlunoBuilder(aluno);
        }
        catch (Exception e) {
            return null;
        }
    }

    private static Aluno create(String nome, String cpf, String rg, Calendar dataNascimento, String telefone, String email, String nacionalidade,
                                      String cidade, String uf, String rga, String sexo, String curso) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setRg(rg);
        aluno.setDataNascimento(dataNascimento);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
        aluno.setNacionalidade(nacionalidade);
        aluno.setCidade(cidade);
        aluno.setUf(uf);
        aluno.setRga(rga);
        aluno.setSexo(Sexo.valueOf(sexo));
        aluno.setCurso(curso);
        return aluno;
    }

    public AlunoBuilder more(String nome, String cpf, String rg, String dataNascimento, String telefone, String email, String nacionalidade,
                             String cidade, String uf, String rga, String sexo, String curso) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            alunos.add(create(nome, cpf, rg, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, rga, sexo, curso));
            return this;
        }
        catch (Exception e) {
            return null;
        }
    }

    public Aluno buildOne() { return alunos.get(0);}

    public List<Aluno> buildAll() {
        return alunos;
    }


}
