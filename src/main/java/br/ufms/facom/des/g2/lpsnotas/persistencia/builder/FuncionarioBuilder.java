package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcionario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FuncionarioBuilder {

    private static SimpleDateFormat strToDate = new SimpleDateFormat("dd/MM/yyyy");
    private List<Funcionario> funcionarios = new ArrayList();

    public FuncionarioBuilder(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public static FuncionarioBuilder newFuncionario(String nome, String cpf, String rg, String senha, String rga, String dataNascimento, String telefone, String email, String nacionalidade,
                                                    String cidade, String uf, String dataAdmissao, double salario, String sexo, Funcao funcao) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            Calendar calendarDataAdmissao = Calendar.getInstance();
            calendarDataAdmissao.setTime(strToDate.parse(dataAdmissao));
            Funcionario funcionario = create(nome, cpf, rg, senha, rga, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, calendarDataAdmissao, salario, sexo, funcao);
            return new FuncionarioBuilder(funcionario);
        }
        catch (Exception e) {
            return null;
        }
    }

    private static Funcionario create(String nome, String cpf, String rg, String senha, String rga, Calendar dataNascimento, String telefone, String email, String nacionalidade,
                                      String cidade, String uf, Calendar dataAdmissao, double salario, String sexo, Funcao funcao) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setRg(rg);
        funcionario.setSenha(senha);
        funcionario.setRga(rga);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setTelefone(telefone);
        funcionario.setEmail(email);
        funcionario.setNacionalidade(nacionalidade);
        funcionario.setCidade(cidade);
        funcionario.setUf(uf);
        funcionario.setDataAdmissao(dataAdmissao);
        funcionario.setSalario(salario);
        funcionario.setSexo(sexo);
        funcionario.setFuncao(funcao);
        return funcionario;
    }

    public  FuncionarioBuilder more(String nome, String cpf, String rg, String senha, String rga, String dataNascimento, String telefone, String email, String nacionalidade,
                                                    String cidade, String uf, String dataAdmissao, double salario, String sexo, Funcao funcao) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            Calendar calendarDataAdmissao = Calendar.getInstance();
            calendarDataAdmissao.setTime(strToDate.parse(dataAdmissao));
            funcionarios.add(create(nome, cpf, rg, senha, rga, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, calendarDataAdmissao, salario, sexo, funcao));
            return this;
        }
        catch (Exception e) {
            return null;
        }
    }

    public Funcionario buildOne() { return funcionarios.get(0);}

    public List<Funcionario> buildAll() {
        return funcionarios;
    }


}
