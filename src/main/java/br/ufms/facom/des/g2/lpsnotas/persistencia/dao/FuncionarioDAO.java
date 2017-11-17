package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.FuncionarioBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcionario;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sexo;

import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FuncionarioDAO extends Dao<Funcionario> {

    private static final FuncaoDAO funcaoDAO = new FuncaoDAO();

    public FuncionarioDAO() {
        super("Funcionário",
                "insert into funcionario(nome, cpf, rg, dataNascimento, telefone, email, nacionalidade, cidade, uf, salario, sexo, cargo, codigoFuncao) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "update funcionario set nome = ?, cpf = ?, rg = ?, dataNascimento = ?, telefone = ?, email = ?, nacionalidade = ?, cidade = ?, uf = ?, salario = ?, sexo = ?, cargo = ?, codigoFuncao = ? where codigo = ?",
                "delete from funcionario where codigo = ?",
                "select * from funcionario where codigo = ?",
                "select * from funcionario order by nome");
    }

    @Override
    public void start() {
        Funcao f = new Funcao();
        f.setCodigo(1);
        FuncionarioBuilder.newFuncionario("Joao Carlos da Silva", "123456789-77", "331321", "02/05/1971",
                "3312-2345", "joao@facom.ufms.br", "Brasileira", "Campo Grande", "MS", 4500, "M", "Técnico Administrativo", f)
                .more("Maria Antonieta da Silva", "434341212-54", "2121", "01/09/1990",
                        "3312-2357", "antonieta@facom.ufms.br", "Brasileira", "Campo Grande", "MS", 2800, "F", "Administrador", f)
                .buildAll().forEach(funcionario -> save(funcionario));
    }

    @Override
    protected Funcionario resultSetToObjet(ResultSet rs) {
        Funcionario funcionario = new Funcionario();
        try {
            funcionario.setCodigo(rs.getLong("codigo"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setRg(rs.getString("rg"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataNascimento")));
            funcionario.setDataNascimento(calendar);
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setNacionalidade(rs.getString("nacionalidade"));
            funcionario.setCidade(rs.getString("cidade"));
            funcionario.setUf(rs.getString("uf"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataAdmissao")));
            funcionario.setSalario(rs.getDouble("salario"));
            funcionario.setSexo(Sexo.valueOf(rs.getString("sexo")));
            funcionario.setCargo(rs.getString("cargo"));
            funcionario.setFuncao(funcaoDAO.findById(rs.getLong("codigoFuncao")));
        }
        catch (Exception e) {
            logger.error(e);
        }
        return funcionario;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        try {
            try {
                if (funcionario.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(13, funcionario.getCodigo());
                }
                pstmt.setString(1, funcionario.getNome());
                pstmt.setString(2, funcionario.getCpf());
                pstmt.setString(3, funcionario.getRg());
                pstmt.setDate(4, new java.sql.Date(funcionario.getDataNascimento().getTime().getTime()));
                pstmt.setString(5, funcionario.getTelefone());
                pstmt.setString(6, funcionario.getEmail());
                pstmt.setString(7, funcionario.getNacionalidade());
                pstmt.setString(8, funcionario.getCidade());
                pstmt.setString(9, funcionario.getUf());
                pstmt.setDouble(10, funcionario.getSalario());
                pstmt.setString(11, funcionario.getSexo().name());
                pstmt.setLong(12, funcionario.getFuncao().getCodigo());
                pstmt.execute();
                if (funcionario.getCodigo() == 0) {
                    funcionario.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", funcionario.exibir()));
        }
        return funcionario;
    }

}
