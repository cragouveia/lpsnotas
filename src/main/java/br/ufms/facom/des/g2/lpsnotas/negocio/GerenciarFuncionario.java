package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.FuncionarioDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Funcionario;

public class GerenciarFuncionario extends Gerenciar<Funcionario> {

    public GerenciarFuncionario() {
        super(new FuncionarioDAO());
    }
}
