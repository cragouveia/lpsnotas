package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.DisciplinaGraduacaoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaGraduacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;

import java.sql.ResultSet;

public class DisciplinaGraduacaoDAO extends Dao<DisciplinaGraduacao> {

    private static final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private Disciplina disciplina;

    public DisciplinaGraduacaoDAO() {
        super("Disciplina de Graduação",
                "insert into disciplinaGraduacao(codigo, eixo) values(?, ?)",
                "update disciplinaGraduacao set eixo = ? where codigo = ?",
                "delete from disciplinaGraduacao where codigo = ?",
                "select d.*, dg.eixo from disciplina d, disciplinaGraduacao dg where d.codigo = dg.codigo and d.codigo = ?",
                "select d.*, dg.eixo from disciplina d, disciplinaGraduacao dg where d.codigo = dg.codigo order by d.nome");
    }

    @Override
    public void createTable() {
        createTable("disciplinagraduacao", "create table disciplinagraduacao (codigo int primary key check (codigo > 0), eixo varchar(100) not null, foreign key (codigo) references disciplina(codigo));");
    }

    @Override
    public void start() {
        DisciplinaGraduacaoBuilder.newDisciplinaGraduacao("Desenvolvimento de Software", "DES", "Desenvolvimento de Software", "Eixo 2")
                .more("Engenharia de Software", "ES", "Engenharia de Software", "Eixo 1")
                .buildAll().forEach(disciplina -> save(disciplina));
    }

    @Override
    protected DisciplinaGraduacao resultSetToObjet(ResultSet rs) {
        DisciplinaGraduacao disciplinaGraduacao = new DisciplinaGraduacao();
        try {
            disciplinaGraduacao.setCodigo(rs.getLong("codigo"));
            disciplinaGraduacao.setNome(rs.getString("nome"));
            disciplinaGraduacao.setSigla(rs.getString("sigla"));
            disciplinaGraduacao.setEmenta(rs.getString("ementa"));
            disciplinaGraduacao.setEixo(rs.getString("eixo"));
        }
        catch (Exception e) {
            logger.error(e);
        }
        return disciplinaGraduacao;
    }

    @Override
    public DisciplinaGraduacao save(DisciplinaGraduacao disciplinaGraduacao) {
        try {
            try {
                if (disciplinaGraduacao.getCodigo() == 0) {
                    disciplina = disciplinaDAO.save(disciplinaGraduacao);
                    disciplinaGraduacao.setCodigo(disciplina.getCodigo());
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                }
                pstmt.setLong(1, disciplinaGraduacao.getCodigo());
                pstmt.setString(2, disciplinaGraduacao.getEixo());
                pstmt.execute();
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", disciplinaGraduacao.exibir()));
        }
        return disciplinaGraduacao;
    }

    @Override
    public void delete(Entidade entidade) {
        super.delete(entidade);
        disciplinaDAO.delete(entidade);
    }

}
