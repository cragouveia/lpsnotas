package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.SalaDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sala;

public class GerenciarSala extends Gerenciar<Sala> {

    public GerenciarSala() {
        super(new SalaDAO());
    }
}
