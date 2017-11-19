package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.negocio.Gerenciar;
import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.FuncaoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcao;

public class GerenciarFuncao extends Gerenciar<Funcao> {

    public GerenciarFuncao() {
        super(new FuncaoDAO());
    }
}
