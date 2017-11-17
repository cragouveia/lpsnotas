package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.TipoDisciplina;

import java.sql.ResultSet;

public class TipoDisciplinaDAO extends Dao<TipoDisciplina> {

    public TipoDisciplinaDAO() {
        super("Tipo de Disciplina",
                "insert into tipodisciplina(descricao) values(?)",
                "update tipodisciplina set descricao = ? where codigo = ?",
                "delete from tipodisciplina where codigo = ?",
                "select * from tipodisciplina where codigo = ?",
                "select * from tipodisciplina order by descricao");
    }

    @Override
    protected TipoDisciplina resultSetToObjet(ResultSet rs) {
        TipoDisciplina tipoDisciplina = new TipoDisciplina();
        try {
            tipoDisciplina.setCodigo(rs.getLong("codigo"));
            tipoDisciplina.setDescricao(rs.getString("descricao"));
        }
        catch (Exception e) {
            logger.error(e);
        }
        return tipoDisciplina;
    }

    @Override
    public TipoDisciplina save(TipoDisciplina tipoDisciplina) {
        try {
            try {
                if (tipoDisciplina.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(2, tipoDisciplina.getCodigo());
                }
                pstmt.setString(1, tipoDisciplina.getDescricao());
                pstmt.execute();
                if (tipoDisciplina.getCodigo() == 0) {
                    tipoDisciplina.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", tipoDisciplina.exibir()));
        }
        return tipoDisciplina;
    }


}
