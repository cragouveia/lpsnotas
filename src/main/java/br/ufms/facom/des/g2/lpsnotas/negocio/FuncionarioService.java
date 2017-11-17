package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.FuncionarioDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcionario;

import java.util.List;

public class FuncionarioService {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void save(Funcionario funcionario) throws Exception{
        funcionarioDAO.save(funcionario);
    }

    public List<Funcionario> getAll() throws Exception{
        return funcionarioDAO.getAll();
    }

}
