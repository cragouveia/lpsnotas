package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.Dao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.DisciplinaPosGraduacaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaPosGraduacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;

import java.util.List;

public class PersistenciaDisciplinaPosGraduacao implements PersistenciaDisciplina<DisciplinaPosGraduacao> {

    private DisciplinaPosGraduacaoDAO dao = new DisciplinaPosGraduacaoDAO();

    @Override
    public Dao getDao() {
        return dao;
    }

    @Override
    public void createTable() throws Exception {
        dao.createTable();
    }

    @Override
    public void start() {
        dao.start();
    }

    @Override
    public DisciplinaPosGraduacao save(DisciplinaPosGraduacao obj) throws Exception {
        return dao.save(obj);
    }

    @Override
    public void delete(Entidade entidade) throws Exception {
        dao.delete(entidade);
    }

    @Override
    public DisciplinaPosGraduacao findById(Long id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public List<DisciplinaPosGraduacao> getAll() throws Exception {
        return dao.getAll();
    }
}
