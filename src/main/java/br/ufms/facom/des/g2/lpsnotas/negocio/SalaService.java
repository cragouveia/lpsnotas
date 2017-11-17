package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.SalaDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sala;

import java.util.List;

public class SalaService {

    private SalaDAO salaDAO = new SalaDAO();

    public void save(Sala sala) throws Exception{
        salaDAO.save(sala);
    }

    public List<Sala> getAll() throws Exception{
        return salaDAO.getAll();
    }

}
