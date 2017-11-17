package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.FuncaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;

import java.util.List;

public class FuncaoService {

    private FuncaoDAO funcaoDAO = new FuncaoDAO();

    public void start() throws Exception {
        Funcao funcao = new Funcao();
        funcao.setNome("Administador");
        funcao.setDescricao("Usuário com permissões de administrar todo o sistema");
        save(funcao);
        funcao = new Funcao();
        funcao.setNome("Professor");
        funcao.setDescricao("Usuário com permissões de gerenciar notas de alunos");
        save(funcao);
        funcao = new Funcao();
        funcao.setNome("Aluno");
        funcao.setDescricao("Usuário com permissões de consulta notas de alunos");
        save(funcao);
    }

    public void save(Funcao funcao) throws Exception{
        funcaoDAO.save(funcao);
    }

    public List<Funcao> getAll() throws Exception{
        return funcaoDAO.getAll();
    }

}
