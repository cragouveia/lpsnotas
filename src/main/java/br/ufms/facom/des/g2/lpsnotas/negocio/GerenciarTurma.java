package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.negocio.strategy.avaliacao.AvaliacaoTurma;
import br.ufms.facom.des.g2.lpsnotas.negocio.strategy.disciplina.CheckDisciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.TurmaDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Turma;

public class GerenciarTurma extends Gerenciar<Turma>{

    private final CheckDisciplina checkDisciplina;
    private AvaliacaoTurma avaliacaoTurma;

    public GerenciarTurma(AvaliacaoTurma avaliacaoTurma, CheckDisciplina checkDisciplina) {
        super(new TurmaDAO());
        this.avaliacaoTurma = avaliacaoTurma;
        this.checkDisciplina = checkDisciplina;
    }

    private boolean validarDisciplina(Disciplina disciplina) {
        return checkDisciplina.validarDisciplina(disciplina);
    }

    private void definirQtdeAvaliacao(Turma turma) {
        avaliacaoTurma.definirQtdeAvaliacao(turma);
    }

    public Turma save(Turma turma) throws Exception{
        definirQtdeAvaliacao(turma);
        if (!validarDisciplina(turma.getDisciplina())) {
            throw new Exception("Essa disciplina não é compatível com o curso.");
        }
        return super.save(turma);
    }
}
