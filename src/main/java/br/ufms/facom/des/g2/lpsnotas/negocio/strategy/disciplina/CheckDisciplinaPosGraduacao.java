package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.DisciplinaPosGraduacaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;

public class CheckDisciplinaPosGraduacao implements CheckDisciplina {
    
    private DisciplinaPosGraduacaoDAO dao = new DisciplinaPosGraduacaoDAO();

    @Override
    public boolean validarDisciplina(Disciplina disciplina) {
        return dao.findById(disciplina.getCodigo()) != null;
    }
}
