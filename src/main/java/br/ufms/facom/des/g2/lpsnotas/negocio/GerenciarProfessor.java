package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.ProfessorDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Professor;

public class GerenciarProfessor extends Gerenciar<Professor> {

    public GerenciarProfessor() {
        super(new ProfessorDAO());
    }
}
