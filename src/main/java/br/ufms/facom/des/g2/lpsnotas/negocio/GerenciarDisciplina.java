package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina.PersistenciaDisciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.DisciplinaGraduacao;

public class GerenciarDisciplina<T> extends Gerenciar<Disciplina>{


    private PersistenciaDisciplina<T> persistenciaDisciplina;

    public GerenciarDisciplina(PersistenciaDisciplina<T> persistenciaDisciplina) {
        super(persistenciaDisciplina.getDao());
        this.persistenciaDisciplina = persistenciaDisciplina;
    }

    @Override
    public Disciplina save(Disciplina obj) throws Exception {
        try {
            return super.save(obj);
        }
        catch (ClassCastException c) {
            if (obj instanceof DisciplinaGraduacao) {
                throw new Exception(String.format("A disciplina %s não pode ser salva por não ser uma disciplina de graduação.", obj.getNome()));
            }
            else {
                throw new Exception(String.format("A disciplina %s não pode ser salva por não ser uma disciplina de pós graduação.", obj.getNome()));
            }
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(Disciplina obj) throws Exception {
        try {
            super.delete(obj);
        }
        catch (ClassCastException c) {
            if (obj instanceof DisciplinaGraduacao) {
                throw new Exception(String.format("A disciplina %s não pode ser excluída por não ser uma disciplina de graduação.", obj.getNome()));
            }
            else {
                throw new Exception(String.format("A disciplina %s não pode ser excluída por não ser uma disciplina de pós graduação.", obj.getNome()));
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
}
