package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Turma;

import java.sql.ResultSet;

public class TurmaDAO extends Dao<Turma> {

    private static final SalaDAO salaDAO = new SalaDAO();
    private static final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static final ProfessorDAO professorDAO = new ProfessorDAO();

    public TurmaDAO() {
        super("Turma",
                "insert into turma(sigla, dataInicio, dataTermino, qtdeAvaliacao, codigoSala, codigoDisciplina, codigoProfessor) values(?, ?, ?, ?, ?, ?, ?)",
                "update turma set sigla = ?, dataInicio = ?, dataTermino = ?, qtdeAvaliacao = ?, codigoSala = ?, codigoDisciplina = ?, codigoProfessor = ? where codigo = ?",
                "delete from turma where codigo = ?",
                "select * from turma where codigo = ?",
                "select * from turma order by sigla");
    }

    @Override
    public void createTable() {
        createTable("turma", "create table disciplina (codigo int primary key AUTO_INCREMENT check (codigo > 0), sigla char(5) not null, horaInicio char(5) not null, horaTermino ementa varchar(5000));");
    }

    @Override
    public void start() {
        /*Funcao f = new Funcao();
        f.setCodigo(1);
        TurmaBuilder.newTurma("Joao Carlos da Silva", "123456789-77", "331321", "02/05/1971",
                "3312-2345", "joao@facom.ufms.br", "Brasileira", "Campo Grande", "MS", 4500, "M", "Técnico Administrativo", f)
                .more("Maria Antonieta da Silva", "434341212-54", "2121", "01/09/1990",
                        "3312-2357", "antonieta@facom.ufms.br", "Brasileira", "Campo Grande", "MS", 2800, "F", "Administrador", f)
                .buildAll().forEach(turma -> save(turma));*/
    }

    @Override
    protected Turma resultSetToObjet(ResultSet rs) {
        Turma turma = new Turma();
        try {
            turma.setCodigo(rs.getLong("codigo"));
            turma.setSigla(rs.getString("sigla"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataInicio")));
            turma.setDataInicio(calendar);
            calendar.setTime(jdbcFormat.parse(rs.getString("dataTermino")));
            turma.setDataTermino(calendar);
            turma.setQtdeAvaliacao(rs.getInt("qtdeAvaliacao"));
            turma.setSala(salaDAO.findById(rs.getLong("codigoSala")));
            turma.setDisciplina(disciplinaDAO.findById(rs.getLong("codigoDisciplina")));
            turma.setProfessor(professorDAO.findById(rs.getLong("codigoProfessor")));
        }
        catch (Exception e) {
            logger.error(e);
        }
        return turma;
    }

    @Override
    public Turma save(Turma turma) {
        try {
            try {
                if (turma.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(8, turma.getCodigo());
                }
                pstmt.setString(1, turma.getSigla());
                pstmt.setDate(2, new  java.sql.Date(turma.getDataInicio().getTime().getTime()));
                pstmt.setDate(3, new java.sql.Date(turma.getDataTermino().getTime().getTime()));
                pstmt.setInt(4, turma.getQtdeAvaliacao());
                pstmt.setLong(5, turma.getSala().getCodigo());
                pstmt.setLong(6, turma.getDisciplina().getCodigo());
                pstmt.setLong(7, turma.getProfessor().getCodigo());
                pstmt.execute();
                if (turma.getCodigo() == 0) {
                    turma.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", turma.exibir()));
        }
        return turma;
    }

}
