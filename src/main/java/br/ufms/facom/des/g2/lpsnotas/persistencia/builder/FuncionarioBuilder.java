package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcionario;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sexo;

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

    public static FuncionarioBuilder newFuncionario(String nome, String cpf, String rg, String dataNascimento, String telefone, String email, String nacionalidade,
                                                    String cidade, String uf, double salario, String sexo, String cargo, Funcao funcao) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            Funcionario funcionario = create(nome, cpf, rg, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, salario, sexo, cargo, funcao);
            return new FuncionarioBuilder(funcionario);
        }
        catch (Exception e) {
            return null;
        }
    }

    private static Funcionario create(String nome, String cpf, String rg, Calendar dataNascimento, String telefone, String email, String nacionalidade,
                                      String cidade, String uf, double salario, String sexo, String cargo, Funcao funcao) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setRg(rg);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setTelefone(telefone);
        funcionario.setEmail(email);
        funcionario.setNacionalidade(nacionalidade);
        funcionario.setCidade(cidade);
        funcionario.setUf(uf);
        funcionario.setSalario(salario);
        funcionario.setSexo(Sexo.valueOf(sexo));
        funcionario.setCargo(cargo);
        funcionario.setFuncao(funcao);
        return funcionario;
    }

    public  FuncionarioBuilder more(String nome, String cpf, String rg, String dataNascimento, String telefone, String email, String nacionalidade,
                                                    String cidade, String uf, double salario, String sexo, String cargo, Funcao funcao) {
        try {
            Calendar calendarDataNascimento = Calendar.getInstance();
            calendarDataNascimento.setTime(strToDate.parse(dataNascimento));
            funcionarios.add(create(nome, cpf, rg, calendarDataNascimento, telefone, email, nacionalidade, cidade, uf, salario, sexo, cargo, funcao));
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
