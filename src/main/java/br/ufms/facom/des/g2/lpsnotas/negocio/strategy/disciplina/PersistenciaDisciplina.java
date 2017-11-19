package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.Dao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;

import java.util.List;

public interface PersistenciaDisciplina<T> {

    Dao getDao();

    void createTable() throws Exception;

    void start() throws Exception;

    T save(T obj) throws Exception;

    void delete(Entidade entidade) throws Exception;

    T findById(Long id) throws Exception;

    List<T> getAll() throws Exception;


}
