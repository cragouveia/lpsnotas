package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.AvaliacaoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Avaliacao;

import java.sql.ResultSet;

public class AvaliacaoDAO extends Dao<Avaliacao> {

    public AvaliacaoDAO() {
        super("Função",
                "insert into avaliacao(nome) values(?,)",
                "update avaliacao set nome = ? where codigo = ?",
                "delete from avaliacao where codigo = ?",
                "select * from avaliacao where codigo = ?",
                "select * from avaliacao order by nome");
    }

    @Override
    public void createTable() throws Exception{
        createTable("avaliacao", "create table avaliacao (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(80) not null);");
    }

    @Override
    public void start() {
        AvaliacaoBuilder.newAvaliacao("AV1")
                .more("AV2")
                .more("Av3")
                .buildAll().forEach(avaliacao -> {try{save(avaliacao);}catch(Exception e){System.out.println(e.getMessage());}});
    }

    @Override
    protected Avaliacao resultSetToObjet(ResultSet rs) throws Exception{
        Avaliacao avaliacao = new Avaliacao();
        try {
            avaliacao.setCodigo(rs.getLong("codigo"));
            avaliacao.setNome(rs.getString("nome"));
        }
        catch (Exception e) {
            throw e;
        }
        return avaliacao;
    }

    @Override
    public Avaliacao save(Avaliacao avaliacao) throws Exception{
        try {
            try {
                if (avaliacao.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(3, avaliacao.getCodigo());
                }
                pstmt.setString(1, avaliacao.getNome());
                pstmt.execute();
                if (avaliacao.getCodigo() == 0) {
                    avaliacao.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", avaliacao.exibir()));
        }
        return avaliacao;
    }



}
