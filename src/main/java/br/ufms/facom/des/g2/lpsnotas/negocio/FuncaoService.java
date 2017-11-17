package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.FuncaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;

import java.util.List;

public class FuncaoService {

    private FuncaoDAO funcaoDAO = new FuncaoDAO();

    public void save(Funcao funcao) throws Exception{
        funcaoDAO.save(funcao);
    }

    public List<Funcao> getAll() throws Exception{
        return funcaoDAO.getAll();
    }

}
