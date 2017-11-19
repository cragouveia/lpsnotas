package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;

import java.sql.ResultSet;

public class DisciplinaDAO extends Dao<Disciplina> {

    public DisciplinaDAO() {
        super("Disciplina",
                "insert into disciplina(nome, sigla, ementa) values(?, ?, ?)",
                "update disciplina set nome = ?, sigla = ?, ementa = ? where codigo = ?",
                "delete from disciplina where codigo = ?",
                "select * from disciplina where codigo = ?",
                "select * from disciplina order by nome");
    }

    @Override
    public void createTable() {
        createTable("disciplina", "create table disciplina (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(100) not null, sigla char(5) not null, ementa varchar(5000));");
    }

    @Override
    public void start() {
    }

    @Override
    protected Disciplina resultSetToObjet(ResultSet rs) {
        return null;
    }

    @Override
    public Disciplina save(Disciplina disciplina) {
        try {
            try {
                if (disciplina.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(5, disciplina.getCodigo());
                }
                pstmt.setString(1, disciplina.getNome());
                pstmt.setString(2, disciplina.getSigla());
                pstmt.setString(3, disciplina.getEmenta());
                pstmt.execute();
                if (disciplina.getCodigo() == 0) {
                    disciplina.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", disciplina.exibir()));
        }
        return disciplina;
    }


}
