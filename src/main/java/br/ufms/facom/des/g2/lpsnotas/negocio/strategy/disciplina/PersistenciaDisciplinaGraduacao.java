package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.Dao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.DisciplinaGraduacaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaGraduacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;

import java.util.List;

public class PersistenciaDisciplinaGraduacao implements PersistenciaDisciplina<DisciplinaGraduacao> {

    private DisciplinaGraduacaoDAO dao = new DisciplinaGraduacaoDAO();

    @Override
    public Dao getDao() {
        return dao;
    }

    @Override
    public void createTable() throws Exception{
        dao.createTable();
    }

    @Override
    public void start() {
        dao.start();
    }

    @Override
    public DisciplinaGraduacao save(DisciplinaGraduacao obj) throws Exception {
        if (obj instanceof DisciplinaGraduacao) {
            return dao.save(obj);
        }
        else {
            throw new Exception(String.format("A disciplina %s não é disciplina de graduação.", obj.getNome()));
        }
    }

    @Override
    public void delete(Entidade entidade)throws Exception {
        if (entidade instanceof DisciplinaGraduacao) {
            dao.delete(entidade);
        }
        else {
            throw new Exception(String.format("A disciplina %s não é disciplina de graduação.", ((DisciplinaGraduacao) entidade).getNome()));
        }
    }

    @Override
    public DisciplinaGraduacao findById(Long id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public List<DisciplinaGraduacao> getAll() throws Exception {
        return dao.getAll();
    }
}
