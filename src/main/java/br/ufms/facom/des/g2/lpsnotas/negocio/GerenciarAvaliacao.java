package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.AvaliacaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Avaliacao;

public class GerenciarAvaliacao extends Gerenciar<Avaliacao> {

    public GerenciarAvaliacao() {
        super(new AvaliacaoDAO());
    }
}
