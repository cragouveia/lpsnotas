package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {

    protected static final Logger logger = Logger.getLogger(Dao.class.getName());

    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
    private static final String DRIVER   = "com.mysql.cj.jdbc.Driver";
    private static final String URL      = "jdbc:mysql://localhost:3306/lpsnotas?createDatabaseIfNotExist=true";

    protected static Connection connection;
    protected PreparedStatement pstmt;
    protected ResultSet rs;

    private static final String [] SQL_TABLE = new String[] {
            "create table funcao (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(80) not null, descricao varchar(250));",
            "create table funcionario (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(80) not null, cpf varchar(15), passaporte varchar(15), rg varchar(15), senha varchar(10),  rga varchar(15), datanascimento date not null, telefone varchar(15), email varchar(60), nacionalidade varchar(50), cidade varchar(100), uf char(2), dataAdmissao date not null, dataDemissao date, salario numeric(10,2), sexo char(1), codigoFuncao int references funcao(codigo));",
            "create table professor (codigo int primary key check (codigo > 0), faculdade varchar(100) not null, foreign key (codigo) references funcionario(codigo));",
            "create table sala (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(80) not null, descricao varchar(250), bloco varchar(10), capacidade int);",
            "create table tipodisciplina (codigo int primary key AUTO_INCREMENT check (codigo > 0), descricao varchar(100) not null);"
    };

    private static final String [] TABLE = new String[] {"funcao", "funcionario", "professor", "sala", "tipodisciplina"};

    private static final String GET_LAST_ID = "select LAST_INSERT_ID()";
    protected String INSERT_SQL;
    protected String UPDATE_SQL;
    protected String DELETE_SQL;
    protected String FIND_BY_ID_SQL;
    protected String GET_ALL_SQL;
    protected String tableName;
    protected String tableDescription;

    static {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }  catch(Exception e) {
            logger.error("Houve um erro ao tentar se conectar com  banco de dados", e);
        }
    }

    public Dao(String tableName, String tableDescription, String INSERT_SQL, String UPDATE_SQL, String DELETE_SQL, String FIND_BY_ID_SQL, String GET_ALL_SQL) {
        super();
        this.tableName = tableName;
        this.INSERT_SQL = INSERT_SQL;
        this.UPDATE_SQL = UPDATE_SQL;
        this.DELETE_SQL = DELETE_SQL;
        this.FIND_BY_ID_SQL = FIND_BY_ID_SQL;
        this.GET_ALL_SQL = GET_ALL_SQL;
        createTable(tableName);
    }

    private void createTable(String tableName) {
        try {
            try {
                for (int i = 0; i < SQL_TABLE.length; i++) {
                    if (tableName.equals(TABLE[i])) {
                        if (!validadeTable(TABLE[i])) {
                            pstmt = connection.prepareStatement(SQL_TABLE[i]);
                            pstmt.executeUpdate();
                            start();
                        }
                    }
                }
            }
            finally {
                pstmt.close();;
            }
        }
        catch (Exception e) {
            logger.error("Houve um erro ao tentar se conectar com  banco de dados", e);
        }

    }

    private boolean validadeTable(String table) {
        try {
            try {
                DatabaseMetaData metadados = connection.getMetaData();
                rs = metadados.getTables(null, null, table, null);
                return rs.next();
            }
            finally {
                rs.close();
            }
        }
        catch (Exception e) {
            logger.error("Houve um erro ao tentar se conectar com  banco de dados", e);
            return false;
        }
    }

    protected abstract void start();

    protected abstract T resultSetToObjet(ResultSet rs);

    public abstract T save(T obj);

    public void delete(Entidade entidade) {
        try {
            try {
                pstmt = connection.prepareStatement(DELETE_SQL);
                pstmt.setLong(1, entidade.getCodigo());
                pstmt.execute();
            }
            finally {
                pstmt.close();
            }
        }
        catch (Exception e ) {
            new Exception(String.format("Houve um erro na tentativa de excluir o objeto %s", entidade.exibir()));
        }
    }

    public T findById(Long id) {
        try {
            try {
                pstmt = connection.prepareStatement(FIND_BY_ID_SQL);
                pstmt.setLong(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    return resultSetToObjet(rs);
                }
            }
            finally {
                rs.close();
                pstmt.close();
            }
        }
        catch(Exception e) {
            new Exception(String.format("Houve um erro na tentativa de recuperar o objeto %s de id %l", tableDescription, id));
        }
        return null;
    }

    public List<T> getAll() {
        List<T> list = new ArrayList();
        try {
            try {
                pstmt = connection.prepareStatement(GET_ALL_SQL);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    list.add(resultSetToObjet(rs));
                }
            }
            finally {
                rs.close();
                pstmt.close();
            }
        }
        catch(Exception e) {
            new Exception(String.format("Houve um erro na tentativa de recuperar a lista de objetos %s", tableDescription));
        }
        return list;
    }

    protected long getCodigoObjeto() {
        long codigo = 0;
        try {
            try {
                pstmt = connection.prepareStatement(GET_LAST_ID);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    codigo = rs.getLong(0);
                }
            }
            finally {
                rs.close();
                pstmt.close();
            }
        }
        catch(Exception e) {
            new Exception(String.format("Houve um erro na tentativa de obter a último ID do objetos %s", tableDescription));
        }
        return codigo;
    }

}
