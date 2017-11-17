package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.ProfessorDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Professor;

import java.util.List;

public class ProfessorService {

    private ProfessorDAO professorDAO = new ProfessorDAO();

    public void save(Professor professor) throws Exception{
        professorDAO.save(professor);
    }

    public List<Professor> getAll() throws Exception{
        return professorDAO.getAll();
    }

}