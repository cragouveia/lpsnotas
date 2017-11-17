package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Professor;

import java.sql.ResultSet;

public class ProfessorDAO extends Dao<Professor> {

    private static final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();


    public ProfessorDAO() {
        super("Professor",
                "insert into professor(codigo, faculdade) values(?, ?)",
                "update professor set faculdade = ? where codigo = ?",
                "delete from professor where codigo = ?",
                "select * from professor where codigo = ?",
                "select * from professor order by descricao");
    }

    @Override
    protected Professor resultSetToObjet(ResultSet rs) {
        Professor professor =  (Professor) funcionarioDAO.resultSetToObjet(rs);
        try {
            professor.setFaculdade(rs.getString("faculdade"));
        }
        catch (Exception e) {
            logger.error(e);
        }
        return professor;
    }

    @Override
    public Professor save(Professor professor) {
        try {
            try {
                funcionarioDAO.save(professor);
                if (professor.getCodigo() == 0) {
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
            new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", professor.exibir()));
        }
        return professor;
    }

    @Override
    public void delete(Entidade entidade) {
        funcionarioDAO.delete(entidade);
        super.delete(entidade);
    }
}
