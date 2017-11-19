package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.negocio.strategy.media.CalculoMedia;
import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.AndamentoIndividualDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.AndamentoIndividual;

public class GerenciarAndamentoIndividual extends Gerenciar<AndamentoIndividual>{

    private CalculoMedia calculoMedia;

    public GerenciarAndamentoIndividual(CalculoMedia calculoMedia) {
        super(new AndamentoIndividualDAO());
        this.calculoMedia = calculoMedia;
    }

    public void calcularMediaFinal(AndamentoIndividual andamentoIndividual) {
        calculoMedia.calcularMediaFinal(andamentoIndividual);
    }

    @Override
    public AndamentoIndividual save(AndamentoIndividual andamentoIndividual) throws Exception {
        if (andamentoIndividual.getNotas().size() > andamentoIndividual.getTurma().getQtdeAvaliacao()) {
            throw new Exception(String.format("Número de avaliações (%d) informado superior ao número máximo de avaliações da turma (%d)",
                    andamentoIndividual.getNotas().size(), andamentoIndividual.getTurma().getQtdeAvaliacao()));
        }
        return super.save(andamentoIndividual);
    }
}
