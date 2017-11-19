package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.Dao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Entidade;

import java.util.List;

public class Gerenciar<T> {


    protected Dao<T> dao;

    public Gerenciar(Dao<T> dao) {
        this.dao = dao;
    }

    public void start() throws Exception{
        try {
            dao.start();
        }
        catch(Exception e) {
           throw e;
        }
    }
    public T save(T obj) throws Exception {
        try {
            return dao.save(obj);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void delete(T obj) throws Exception{
        try {
            dao.delete((Entidade) obj);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public T findById(long id) throws Exception{
        try {
            return dao.findById(id);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public List<T> getAll() throws Exception{
        try {
            return dao.getAll();
        }
        catch(Exception e) {
            throw e;
        }
    }
}
