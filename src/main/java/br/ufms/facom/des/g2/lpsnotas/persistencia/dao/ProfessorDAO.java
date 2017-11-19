package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.ProfessorBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.*;

import java.sql.ResultSet;
import java.util.List;

public class ProfessorDAO extends Dao<Professor> {

    private static final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static final FuncaoDAO funcaoDAO = new FuncaoDAO();
    private Funcionario funcionario;

    public ProfessorDAO() {
        super("Professor",
                "insert into professor(codigo, faculdade) values(?, ?)",
                "update professor set faculdade = ? where codigo = ?",
                "delete from professor where codigo = ?",
                "select f.*, p.faculdade from funcionario f, professor p where f.codigo = p.codigo and f.codigo = ?",
                "select f.*, p.faculdade from funcionario f, professor p where f.codigo = p.codigo order by f.nome");
    }

    @Override
    public void createTable() throws Exception{
        createTable("professor", "create table professor (codigo int primary key check (codigo > 0), faculdade varchar(100) not null, foreign key (codigo) references funcionario(codigo));");
    }

    @Override
    public void start() {
        Funcao f = new Funcao();
        f.setCodigo(2);
        ProfessorBuilder.newProfessor("Tiago Alencar", "312121214-77", "331321", "02/05/1971",
                "3312-2345", "tiago@facom.ufms.br", "Brasileira", "Campo Grande", "MS", 10000, "M", "Professor", f, "FACOM")
                .more("Amanda de Oliveira", "564567891-54", "2121", "01/09/1990",
                        "3312-2131", "amanda@facom.ufms.br", "Brasileira", "Campo Grande", "MS", 10000, "F", "Professor", f, "FACOM")
                .buildAll().forEach(professor -> {try{save(professor);}catch(Exception e){System.out.println(e.getMessage());}});
    }

    @Override
    protected Professor resultSetToObjet(ResultSet rs) throws Exception{
        Professor professor = new Professor();
        try {
            professor.setCodigo(rs.getLong("codigo"));
            professor.setNome(rs.getString("nome"));
            professor.setCpf(rs.getString("cpf"));
            professor.setRg(rs.getString("rg"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataNascimento")));
            professor.setDataNascimento(calendar);
            professor.setTelefone(rs.getString("telefone"));
            professor.setEmail(rs.getString("email"));
            professor.setNacionalidade(rs.getString("nacionalidade"));
            professor.setCidade(rs.getString("cidade"));
            professor.setUf(rs.getString("uf"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataAdmissao")));
            professor.setSalario(rs.getDouble("salario"));
            professor.setSexo(Sexo.valueOf(rs.getString("sexo")));
            professor.setCargo(rs.getString("cargo"));
            professor.setFuncao(funcaoDAO.findById(rs.getLong("codigoFuncao")));
            professor.setFaculdade(rs.getString("faculdade"));
        }
        catch (Exception e) {
            throw e;
        }
        return professor;
    }

    @Override
    public Professor save(Professor professor) throws Exception{
        try {
            try {
                if (professor.getCodigo() == 0) {
                    funcionario = funcionarioDAO.save(professor);
                    professor.setCodigo(funcionario.getCodigo());
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                }
                pstmt.setLong(1, professor.getCodigo());
                pstmt.setString(2, professor.getFaculdade());
                pstmt.execute();
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", professor.exibir()));
        }
        return professor;
    }

    @Override
    public void delete(Entidade entidade) throws Exception {
        super.delete(entidade);
        funcionarioDAO.delete(entidade);
    }

}
