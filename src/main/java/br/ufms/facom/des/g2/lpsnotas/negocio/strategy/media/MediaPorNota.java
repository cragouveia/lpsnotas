package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.media;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.AndamentoIndividual;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.NotaAvaliacao;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Status;

import java.util.List;
import java.util.stream.Collectors;

public class MediaPorNota implements CalculoMedia {

    @Override
    public void calcularMediaFinal(AndamentoIndividual andamentoIndividual) {
        List<NotaAvaliacao> notas = andamentoIndividual.getNotas();
        double media = notas.stream().collect(Collectors.averagingDouble(NotaAvaliacao::getNota));
        andamentoIndividual.setMediaFinal(media);
        andamentoIndividual.setStatus(media >= 7 ? Status.APROVADO : Status.REPROVADO);
    }
}
