package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.AndamentoIndividual;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.NotaAvaliacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sexo;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Status;

import java.sql.ResultSet;

public class AndamentoIndividualDAO extends Dao<AndamentoIndividual> {

    private static final AlunoDAO alunoDAO = new AlunoDAO();
    private static final ProfessorDAO professorDAO = new ProfessorDAO();
    private static final TurmaDAO turmaDAO = new TurmaDAO();

    public AndamentoIndividualDAO() {
        super("Andamento Individual",
                "insert into andamentoindividual(mediafinal, status, codigoaluno, codigoprofessor, codigoturma) values(?, ?, ?, ?, ?)",
                "update andamentoindividual set mediafinal  = ?, status = ?, codigoaluno = ?, codigoprofessor = ?, codigoturma = ? where codigo = ?",
                "delete from andamentoindividual where codigo = ?",
                "select * from andamentoindividual where codigo = ?",
                "select * from andamentoindividual order by codigo");
    }

    @Override
    public void createTable() {
        createTable("andamentoindividual", "create table andamentoindividual (codigo int primary key AUTO_INCREMENT check (codigo > 0), mediaFinal numeric(5,2) not null, status char(1), codigoaluno int not null references aluno(codigo), codigoprofessor int not null references professor(codigo), codigoturma int not null references turma(codigo));");
        createTable("notaavaliacao", "create table notaavaliacao (codigo int primary key AUTO_INCREMENT check (codigo > 0), codigoAndamento int not null references andamentoindividual(codigo), codigoAvaliacao int not null references avaliacao(codigo), nota double, conceito char(1));");
    }

    @Override
    public void start() {
/*
        AndamentoIndividualBuilder.newAndamentoIndividual("Joao Carlos da Silva", "123456789-77", "331321", "02/05/1971",
                "3312-2345", "joao@facom.ufms.br", "Brasileira", "Campo Grande", "MS", "332323", "M", "Direito")
                .more("Maria Antonieta da Silva", "434341212-54", "2121", "01/09/1990",
                        "3312-2357", "antonieta@facom.ufms.br", "Brasileira", "Campo Grande", "MS", "dfdf", "F", "Administração")
                .buildAll().forEach(andamentoIndividual -> save(andamentoIndividual));
*/
    }

    @Override
    protected AndamentoIndividual resultSetToObjet(ResultSet rs) {
        AndamentoIndividual andamentoIndividual = new AndamentoIndividual();
        try {
            andamentoIndividual.setCodigo(rs.getLong("codigo"));
            andamentoIndividual.setMediaFinal(rs.getDouble("mediaFinal"));
            andamentoIndividual.setStatus(Status.valueOf(rs.getString("status")));
            andamentoIndividual.setAluno(alunoDAO.findById(rs.getLong("codigoAluno")));
            andamentoIndividual.setProfessor(professorDAO.findById(rs.getLong("codigoProfessor")));
            andamentoIndividual.setTurma(turmaDAO.findById(rs.getLong("codigoTurma")));
        }
        catch (Exception e) {
            logger.error(e);
        }
        return andamentoIndividual;
    }

    @Override
    public AndamentoIndividual save(AndamentoIndividual andamentoIndividual) {
        try {
            connection.setAutoCommit(false);
            try {
                if (andamentoIndividual.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(6, andamentoIndividual.getCodigo());
                }
                pstmt.setDouble(1, andamentoIndividual.getMediaFinal());
                pstmt.setString(2, andamentoIndividual.getStatus().name());
                pstmt.setLong(3, andamentoIndividual.getAluno().getCodigo());
                pstmt.setLong(4, andamentoIndividual.getProfessor().getCodigo());
                pstmt.setLong(5, andamentoIndividual.getTurma().getCodigo());
                pstmt.execute();
                if (andamentoIndividual.getCodigo() == 0) {
                    andamentoIndividual.setCodigo(getCodigoObjeto());
                }
                // para evitar a verificacao se a nota cada nota ja esta salva ou nao
                // iremos excluir todas as notas e salva-las novamente
                pstmt = connection.prepareStatement("delete from notaavaliacao where codigoandamento = ?");
                pstmt.setLong(1, andamentoIndividual.getCodigo());
                pstmt.execute();
                //
                pstmt = connection.prepareStatement("insert into notaavaliacao(nota, conceito, codigoavaliacao, codigoandamento) values(?, ?, ?, ?)");
                for (NotaAvaliacao notaAvaliacao: andamentoIndividual.getNotas()) {
                    pstmt.setDouble(1, notaAvaliacao.getNota());
                    pstmt.setString(2, notaAvaliacao.getConceito() != null ? notaAvaliacao.getConceito().name() : "");
                    pstmt.setLong(3, notaAvaliacao.getAvaliacao().getCodigo());
                    pstmt.setLong(4, notaAvaliacao.getAndamentoIndividual().getCodigo());
                    pstmt.execute();
                }
                connection.commit();
            }finally {
                pstmt.close();
                connection.setAutoCommit(true);
            }
        }
        catch (Exception e) {
            try {
                connection.rollback();
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
            new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", andamentoIndividual.exibir()));
        }
        return andamentoIndividual;
    }




}
