package br.ufms.facom.des.g2.lpsnotas.persistencia.dao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.builder.AlunoBuilder;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Aluno;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sexo;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Turma;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends Dao<Aluno> {

    public AlunoDAO() {
        super("Funcionário",
                "insert into aluno(nome, cpf, rg, dataNascimento, telefone, email, nacionalidade, cidade, uf, rga, sexo, curso) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "update aluno set nome = ?, cpf = ?, rg = ?, dataNascimento = ?, telefone = ?, email = ?, nacionalidade = ?, cidade = ?, uf = ?, rga = ?, sexo = ?, curso = ? where codigo = ?",
                "delete from aluno where codigo = ?",
                "select * from aluno where codigo = ?",
                "select * from aluno order by nome");
    }

    @Override
    public void createTable() throws Exception {
        createTable("aluno", "create table aluno (codigo int primary key AUTO_INCREMENT check (codigo > 0), nome varchar(80) not null, cpf varchar(15), rg varchar(15), datanascimento date not null, telefone varchar(15), email varchar(60), nacionalidade varchar(50), cidade varchar(100), uf char(2), rga varchar(15), sexo char(1), curso varchar(50));");
        createTable("alunoturma", "create table alunoturma (codigo int primary key AUTO_INCREMENT check (codigo > 0), codigoAluno int not null references aluno(codigo), codigoTurma int not null references turma(codigo));");
    }

    @Override
    public void start() throws Exception {
        try {
            AlunoBuilder.newAluno("Joao Carlos da Silva", "123456789-77", "331321", "02/05/1971",
                    "3312-2345", "joao@facom.ufms.br", "Brasileira", "Campo Grande", "MS", "332323", "M", "Direito")
                    .more("Maria Antonieta da Silva", "434341212-54", "2121", "01/09/1990",
                            "3312-2357", "antonieta@facom.ufms.br", "Brasileira", "Campo Grande", "MS", "dfdf", "F", "Administração")
                    .buildAll().forEach(aluno -> {try {save(aluno);}catch(Exception e){System.out.println(e.getMessage());}});
        }
        catch (Exception e) {

        }
    }

    @Override
    protected Aluno resultSetToObjet(ResultSet rs) throws Exception{
        Aluno aluno = new Aluno();
        try {
            aluno.setCodigo(rs.getLong("codigo"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setRg(rs.getString("rg"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataNascimento")));
            aluno.setDataNascimento(calendar);
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setEmail(rs.getString("email"));
            aluno.setNacionalidade(rs.getString("nacionalidade"));
            aluno.setCidade(rs.getString("cidade"));
            aluno.setUf(rs.getString("uf"));
            calendar.setTime(jdbcFormat.parse(rs.getString("dataAdmissao")));
            aluno.setRga(rs.getString("rga"));
            aluno.setSexo(Sexo.valueOf(rs.getString("sexo")));
            aluno.setCurso(rs.getString("cargo"));
        }
        catch (Exception e) {
            throw e;
        }
        return aluno;
    }

    @Override
    public Aluno save(Aluno aluno) throws Exception{
        try {
            try {
                if (aluno.getCodigo() == 0) {
                    pstmt = connection.prepareStatement(INSERT_SQL);
                } else {
                    pstmt = connection.prepareStatement(UPDATE_SQL);
                    pstmt.setLong(13, aluno.getCodigo());
                }
                pstmt.setString(1, aluno.getNome());
                pstmt.setString(2, aluno.getCpf());
                pstmt.setString(3, aluno.getRg());
                pstmt.setDate(4, new java.sql.Date(aluno.getDataNascimento().getTime().getTime()));
                pstmt.setString(5, aluno.getTelefone());
                pstmt.setString(6, aluno.getEmail());
                pstmt.setString(7, aluno.getNacionalidade());
                pstmt.setString(8, aluno.getCidade());
                pstmt.setString(9, aluno.getUf());
                pstmt.setString(10, aluno.getRga());
                pstmt.setString(11, aluno.getSexo().name());
                pstmt.setString(12, aluno.getCurso());
                pstmt.execute();
                if (aluno.getCodigo() == 0) {
                    aluno.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de salvar o objeto %s", aluno.exibir()));
        }
        return aluno;
    }

    public void matricularAluno(Aluno aluno, Turma turma) throws Exception{
        try {
            try {
                pstmt = connection.prepareStatement("insert into alunoturma(codigoaluno, codigoturma) values (?, ?)");
                pstmt.setLong(1, aluno.getCodigo());
                pstmt.setLong(2, turma.getCodigo());
                pstmt.execute();
                if (aluno.getCodigo() == 0) {
                    aluno.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de matricular o aluno %s na turma %s", aluno.getNome(), turma.getSigla()));
        }
    }

    public void cancelarMatriculaAluno(Aluno aluno, Turma turma) throws Exception{
        try {
            try {
                pstmt = connection.prepareStatement("delete from alunoturma where codigoaluno ? and codigoturma = ?");
                pstmt.setLong(1, aluno.getCodigo());
                pstmt.setLong(2, turma.getCodigo());
                pstmt.execute();
                if (aluno.getCodigo() == 0) {
                    aluno.setCodigo(getCodigoObjeto());
                }
            }finally {
                pstmt.close();
            }
        }
        catch (Exception e) {
            throw new Exception(String.format("Houve um erro na tentativa de cancelar a matricula o aluno %s na turma %s", aluno.getNome(), turma.getSigla()));
        }
    }

    public List<Aluno> consultarAlunosPorDisciplina(Disciplina disciplina) throws Exception{
        List<Aluno> list = new ArrayList();
        try {
            try {
                pstmt = connection.prepareStatement("select a.* from aluno a, alunoturma at, turma t where a.codigo = at.codigoaluno and at.codigoturma = t.codigo and t.codigodisciplina = ? order by a.nome");
                pstmt.setLong(1, disciplina.getCodigo());
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
           throw new Exception(String.format("Houve um erro na tentativa de recuperar a lista de alunos da disciplina %s", disciplina.getNome()));
        }
        return list;
    }


    public List<Aluno> consultarAlunosPorTurma(Turma turma) throws Exception{
        List<Aluno> list = new ArrayList();
        try {
            try {
                pstmt = connection.prepareStatement("select a.* from aluno a, alunoturma at where a.codigo = at.codigoaluno and at.codigoturma = ? order by a.nome");
                pstmt.setLong(1, turma.getCodigo());
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
            throw new Exception(String.format("Houve um erro na tentativa de recuperar a lista de alunos da turma %s", turma.getSigla()   ));
        }
        return list;
    }

    public List<Aluno> listarAlunosGraduacao() throws Exception{
        List<Aluno> list = new ArrayList();
        try {
            try {
                pstmt = connection.prepareStatement("select a.* from aluno a, alunoturma at, turma t, disciplina d, disciplinagraduacao gd where a.codigo = at.codigoaluno and at.codigoturma = t.codigo and t.codigodisciplina = d.codigo and d.codigo = dg.codigo order by t.sigla, a.nome");
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
            throw new Exception("Houve um erro na tentativa de recuperar a lista de alunos de graduação");
        }
        return list;
    }

    public List<Aluno> listarAlunosPosGraduacao() throws Exception{
        List<Aluno> list = new ArrayList();
        try {
            try {
                pstmt = connection.prepareStatement("select a.* from aluno a, alunoturma at, turma t, disciplina d, disciplinaposgraduacao gpd where a.codigo = at.codigoaluno and at.codigoturma = t.codigo and t.codigodisciplina = d.codigo and d.codigo = dpg.codigo order by t.sigla,  a.nome");
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
            throw new Exception("Houve um erro na tentativa de recuperar a lista de alunos de pós graduação");
        }
        return list;
    }

}
