package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.FuncaoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;

import java.sql.ResultSet;

public class FuncaoDAO extends Dao<Funcao> {

    public FuncaoDAO() {
        super("Função",
                "insert into funcao(nome, descricao) values(?, ?)",
                "update funcao set nome = ?, descricao = ? where codigo = ?",
                "delete from funcao where codigo = ?",
                "select * from funcao where codigo = ?",
                "select * from funcao order by nome");
    }

    @Override
    public void createTable() throws Exception{
        createTable("funcao", "create table funcao (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(80) not null, descricao varchar(250));");
    }

    @Override
    public void start() {
        FuncaoBuilder.newFuncao("Administador", "Usuário com permissões de administrar todo o sistema")
                .more("Professor", "Usuário com permissões de gerenciar notas de alunos")
                .more("Aluno", "Usuário com permissões de consulta notas de alunos")
                .buildAll().forEach(funcao -> {try{save(funcao);}catch(Exception e){System.out.println(e.getMessage());}});
    }

    @Override
    protected Funcao resultSetToObjet(ResultSet rs) throws Exception{
        Funcao funcao = new Funcao();
        try {
            funcao.setCodigo(rs.getLong("codigo"));
            funcao.setNome(rs.getString("nome"));
            funcao.setDescricao(rs.getString("descricao"));
        }
        catch (Exception e) {
            throw e;
        }
        return funcao;
    }

    @Override
    public Funcao save(Funcao funcao) throws Exception{
        try {
            try {
                if (funcao.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(3, funcao.getCodigo());
                }
                pstmt.setString(1, funcao.getNome());
                pstmt.setString(2, funcao.getDescricao());
                pstmt.execute();
                if (funcao.getCodigo() == 0) {
                    funcao.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", funcao.exibir()));
        }
        return funcao;
    }



}
