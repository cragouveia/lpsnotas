package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.FuncaoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.SalaBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sala;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO extends Dao<Sala> {

    public SalaDAO() {
        super("Sala",
                "insert into sala(nome, descricao, bloco, capacidade) values(?, ?, ?, ?)",
                "update sala set nome = ?, descricao = ?, bloco = ?, capacidade = ? where codigo = ?",
                "delete from sala where codigo = ?",
                "select * from sala where codigo = ?",
                "select * from sala order by nome");
    }

    @Override
    public void createTable() throws Exception{
        createTable("sala", "create table sala (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(80) not null, descricao varchar(250), bloco varchar(10), capacidade int);");
    }

    @Override
    public void start() {
        SalaBuilder.newSala("Sala 1", "Sala multimeios", "Bloco A", 20)
                .more("Sala 2", "Laboratório de Pós Graduação", "Bloco B", 30)
                .buildAll().forEach(sala -> {try{save(sala);}catch(Exception e){System.out.println(e.getMessage());}});
    }

    @Override
    protected Sala resultSetToObjet(ResultSet rs) throws Exception{
        Sala sala = new Sala();
        try {
            sala.setCodigo(rs.getLong("codigo"));
            sala.setNome(rs.getString("nome"));
            sala.setDescricao(rs.getString("descricao"));
            sala.setBloco(rs.getString("bloco"));
            sala.setCapacidade(rs.getInt("capacidade"));
        }
        catch (Exception e) {
            throw e;
        }
        return sala;
    }

    @Override
    public Sala save(Sala sala) throws Exception{
        try {
            try {
                if (sala.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(5, sala.getCodigo());
                }
                pstmt.setString(1, sala.getNome());
                pstmt.setString(2, sala.getDescricao());
                pstmt.setString(3, sala.getBloco());
                pstmt.setInt(4, sala.getCapacidade());
                pstmt.execute();
                if (sala.getCodigo() == 0) {
                    sala.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", sala.exibir()));
        }
        return sala;
    }


}
