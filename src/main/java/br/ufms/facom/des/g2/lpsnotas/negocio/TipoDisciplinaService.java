package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.TipoDisciplinaDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.TipoDisciplina;

import java.util.List;

public class TipoDisciplinaService {

    private TipoDisciplinaDAO tipoDisciplinaDAO = new TipoDisciplinaDAO();

    public void save(TipoDisciplina tipoDisciplina) throws Exception{
        tipoDisciplinaDAO.save(tipoDisciplina);
    }

    public List<TipoDisciplina> getAll() throws Exception{
        return tipoDisciplinaDAO.getAll();
    }

}
