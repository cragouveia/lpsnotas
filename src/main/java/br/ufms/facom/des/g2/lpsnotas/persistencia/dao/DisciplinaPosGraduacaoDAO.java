package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.DisciplinaPosGraduacaoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaPosGraduacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;

import java.sql.ResultSet;

public class DisciplinaPosGraduacaoDAO extends Dao<DisciplinaPosGraduacao> {

    private static final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private Disciplina disciplina;

    public DisciplinaPosGraduacaoDAO() {
        super("Disciplina de Graduação",
                "insert into disciplinaPosGraduacao(codigo, area) values(?, ?)",
                "update disciplinaPosGraduacao set area = ? where codigo = ?",
                "delete from disciplinaPosGraduacao where codigo = ?",
                "select d.*, dpg.area from disciplina d, disciplinaPosGraduacao dpg where d.codigo = dpg.codigo and d.codigo = ?",
                "select d.*, dpg.eixo from disciplina d, disciplinaPosGraduacao dpg where d.codigo = dpg.codigo order by d.nome");
    }

    @Override
    public void createTable() throws Exception{
        createTable("disciplinaposgraduacao", "create table disciplinaposgraduacao (codigo int primary key check (codigo > 0), area varchar(100) not null, foreign key (codigo) references disciplina(codigo));");
    }

    @Override
    public void start() {
        DisciplinaPosGraduacaoBuilder.newDisciplinaPosGraduacao("Desenvolvimento de Software", "DES", "Desenvolvimento de Software", "Area 1")
                .more("Engenharia de Software", "ES", "Engenharia de Software", "Eixo 2")
                .buildAll().forEach(disciplina -> {try{save(disciplina);}catch(Exception e){System.out.println(e.getMessage());}});
    }

    @Override
    protected DisciplinaPosGraduacao resultSetToObjet(ResultSet rs) throws Exception{
        DisciplinaPosGraduacao disciplinaPosGraduacao = new DisciplinaPosGraduacao();
        try {
            disciplinaPosGraduacao.setCodigo(rs.getLong("codigo"));
            disciplinaPosGraduacao.setNome(rs.getString("nome"));
            disciplinaPosGraduacao.setSigla(rs.getString("sigla"));
            disciplinaPosGraduacao.setEmenta(rs.getString("ementa"));
            disciplinaPosGraduacao.setArea(rs.getString("area"));
        }
        catch (Exception e) {
           throw e;
        }
        return disciplinaPosGraduacao;
    }

    @Override
    public DisciplinaPosGraduacao save(DisciplinaPosGraduacao disciplinaPosGraduacao) throws Exception{
        try {
            try {
                if (disciplinaPosGraduacao.getCodigo() == 0) {
                    disciplina = disciplinaDAO.save(disciplinaPosGraduacao);
                    disciplinaPosGraduacao.setCodigo(disciplina.getCodigo());
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                }
                pstmt.setLong(1, disciplinaPosGraduacao.getCodigo());
                pstmt.setString(2, disciplinaPosGraduacao.getArea());
                pstmt.execute();
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", disciplinaPosGraduacao.exibir()));
        }
        return disciplinaPosGraduacao;
    }

    @Override
    public void delete(Entidade entidade) throws Exception{
        super.delete(entidade);
        disciplinaDAO.delete(entidade);
    }

}
