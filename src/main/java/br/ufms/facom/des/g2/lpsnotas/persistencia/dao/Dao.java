package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Dao<T> {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
    private static final String DRIVER   = "com.mysql.cj.jdbc.Driver";
    private static final String URL      = "jdbc:mysql://localhost:3306/lpsnotas?createDatabaseIfNotExist=true";

    protected static final SimpleDateFormat jdbcFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected static final Calendar calendar = Calendar.getInstance();
    protected static Connection connection;
    protected PreparedStatement pstmt;
    protected ResultSet rs;

    private static final String GET_LAST_ID = "select LAST_INSERT_ID()";
    protected String INSERT_SQL;
    protected String UPDATE_SQL;
    protected String DELETE_SQL;
    protected String FIND_BY_ID_SQL;
    protected String GET_ALL_SQL;
    protected String tableDescription;

    static  {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }  catch(Exception e) {
            System.out.printf("Houve um erro ao tentar se conectar com  banco de dados");
        }
    }

    public Dao(String tableDescription,String INSERT_SQL, String UPDATE_SQL, String DELETE_SQL, String FIND_BY_ID_SQL, String GET_ALL_SQL) {
        super();
        this.tableDescription = tableDescription;
        this.INSERT_SQL = INSERT_SQL;
        this.UPDATE_SQL = UPDATE_SQL;
        this.DELETE_SQL = DELETE_SQL;
        this.FIND_BY_ID_SQL = FIND_BY_ID_SQL;
        this.GET_ALL_SQL = GET_ALL_SQL;
        try {
            createTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void createTable(String table, String sql) throws Exception{
        try {
            try {
                if (!validadeTable(table)) {
                    pstmt = connection.prepareStatement(sql);
                    pstmt.executeUpdate();
                }
            }
            finally {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
        }
        catch (Exception e) {
           throw new Exception(String.format("Houve um erro na tentativa de criar a tabela %s", table));
        }
    }

    private boolean validadeTable(String table) throws Exception {
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
            throw new Exception(String.format("Houve um erro na tentativa de verificar a existência da tabela %s", table));
        }
    }

    public abstract void createTable() throws Exception;
    public abstract void start() throws Exception;

    protected abstract T resultSetToObjet(ResultSet rs) throws Exception;

    public abstract T save(T obj) throws Exception;

    public void delete(Entidade entidade) throws Exception {
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
           throw new Exception(String.format("Houve um erro na tentativa de excluir o objeto %s", entidade.exibir()));
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

    public List<T> getAll() throws Exception{
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
            throw new Exception(String.format("Houve um erro na tentativa de recuperar a lista de objetos %s", tableDescription));
        }
        return list;
    }

    protected long getCodigoObjeto() throws Exception{
        long codigo = 0;
        try {
            try {
                pstmt = connection.prepareStatement(GET_LAST_ID);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    codigo = rs.getLong(1);
                }
            }
            finally {
                rs.close();
                pstmt.close();
            }
        }
        catch(Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de obter a último ID do objetos %s", tableDescription));
        }
        return codigo;
    }

}
