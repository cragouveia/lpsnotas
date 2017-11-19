package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.DisciplinaGraduacaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;

public class CheckDisciplinaGraduacao implements CheckDisciplina {

    private DisciplinaGraduacaoDAO dao = new DisciplinaGraduacaoDAO();

    @Override
    public boolean validarDisciplina(Disciplina disciplina) {
        return dao.findById(disciplina.getCodigo()) != null;
    }
}
