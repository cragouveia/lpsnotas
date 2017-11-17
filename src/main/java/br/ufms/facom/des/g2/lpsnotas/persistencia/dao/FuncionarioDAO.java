package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.FuncionarioBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcionario;

import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FuncionarioDAO extends Dao<Funcionario> {

    private static final SimpleDateFormat jdbcFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Calendar calendar = Calendar.getInstance();
    private static final FuncaoDAO funcaoDAO = new FuncaoDAO();

    public FuncionarioDAO() {
        super("funcionario",
                "Funcionário",
                "insert into funcionario(nome, cpf, passaporte, rg, senha, rga, dataNascimento, telefone, email, nacionalidade, cidade, uf, dataAdmissao, dataDemissao, salario, sexo, codigoFuncao) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "update funcionario set nome = ?, cpf = ?, passaporte = ?, rg = ?, senha = ?, rga = ?, dataNascimento = ?, telefone = ?, email = ?, nacionalidade = ?, cidade = ?, uf = ?, dataAdmissao = ?, dataDemissao = ?, salario = ?, sexo = ?, codigoFuncao = ? where codigo = ?",
                "delete from funcionario where codigo = ?",
                "select * from funcionario where codigo = ?",
                "select * from funcionario order by nome");
    }

    @Override
    protected void start() {
        Funcao f = new Funcao();
        f.setCodigo(1);
        FuncionarioBuilder.newFuncionario("Joao Carlos da Silva", "123456789-77", "331321", "senha123", "123456", "02/05/1971",
                "3312-2345", "joao@facom.ufms.br", "Brasileira", "Campo Grande", "MS", "01/02/1989", 4500, "M", f)
                .more("Maria Antonieta da Silva", "434341212-54", "2121", "senha123", "456789", "01/09/1990",
                        "3312-2357", "antonieta@facom.ufms.br", "Brasileira", "Campo Grande", "MS", "01/06/2009", 2800, "F", f)
                .buildAll().forEach(funcionario -> save(funcionario));
    }

    @Override
    protected Funcionario resultSetToObjet(ResultSet rs) {
        Funcionario funcionario = new Funcionario();
        try {
            funcionario.setCodigo(rs.getLong("codigo"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setPassaporte(rs.getString("passaporte"));
            funcionario.setRg(rs.getString("rg"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setRga(rs.getString("rga"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataNascimento")));
            funcionario.setDataNascimento(calendar);
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setNacionalidade(rs.getString("nacionalidade"));
            funcionario.setCidade(rs.getString("cidade"));
            funcionario.setUf(rs.getString("uf"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataAdmissao")));
            funcionario.setDataAdmissao(calendar);
            try {
                calendar.setTime(jdbcFormat.parse(rs.getString("dataDemissao")));
                funcionario.setDataDemissao(calendar);
            }
            catch (Exception e) {
                funcionario.setDataDemissao(null);
            }
            funcionario.setSalario(rs.getDouble("salario"));
            funcionario.setSexo(rs.getString("sexo"));
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
                    pstmt.setLong(18, funcionario.getCodigo());
                }
                pstmt.setString(1, funcionario.getNome());
                pstmt.setString(2, funcionario.getCpf());
                pstmt.setString(3, funcionario.getPassaporte());
                pstmt.setString(4, funcionario.getRg());
                pstmt.setString(5, funcionario.getSenha());
                pstmt.setString(6, funcionario.getRga());
                pstmt.setDate(7, new java.sql.Date(funcionario.getDataNascimento().getTime().getTime()));
                pstmt.setString(8, funcionario.getTelefone());
                pstmt.setString(9, funcionario.getEmail());
                pstmt.setString(10, funcionario.getNacionalidade());
                pstmt.setString(11, funcionario.getCidade());
                pstmt.setString(12, funcionario.getUf());
                pstmt.setDate(13, new java.sql.Date(funcionario.getDataAdmissao().getTime().getTime()));
                pstmt.setDate(14, funcionario.getDataDemissao() == null ? null : new java.sql.Date(funcionario.getDataDemissao().getTime().getTime()));
                pstmt.setDouble(15, funcionario.getSalario());
                pstmt.setString(16, funcionario.getSexo());
                pstmt.setLong(17, funcionario.getFuncao().getCodigo());
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
