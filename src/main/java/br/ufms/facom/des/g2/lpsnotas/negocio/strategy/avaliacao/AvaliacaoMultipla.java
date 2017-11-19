package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.avaliacao;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Turma;

public class AvaliacaoMultipla implements AvaliacaoTurma{
    @Override
    public void definirQtdeAvaliacao(Turma turma) {
        turma.setQtdeAvaliacao(-1);
    }
}
