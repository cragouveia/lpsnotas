package br.ufms.facom.des.g2.lpsnotas.persistencia.builder;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Professor;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Sala;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Turma;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TurmaBuilder {

    private static SimpleDateFormat strToDate = new SimpleDateFormat("dd/MM/yyyy");
    private List<Turma> turmas = new ArrayList();

    public TurmaBuilder(Turma turma) {
        turmas.add(turma);
    }

    public static TurmaBuilder newTurma(String sigla, String dataInicio, String dataTermino, int qtdeAvaliacao, Sala sala, Disciplina disciplina, Professor professor) {
        try {
            Calendar calendarDataInicio = Calendar.getInstance();
            calendarDataInicio.setTime(strToDate.parse(dataInicio));
            Calendar calendarDataTermino = Calendar.getInstance();
            calendarDataTermino.setTime(strToDate.parse(dataTermino));
            Turma turma = create(sigla, calendarDataInicio, calendarDataTermino, qtdeAvaliacao, sala, disciplina, professor);
            return new TurmaBuilder(turma);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Turma create(String sigla, Calendar dataInicio, Calendar dataTermino, int qtdeAvaliacao, Sala sala, Disciplina disciplina, Professor professor) {
        Turma turma = new Turma();
        turma.setSigla(sigla);
        turma.setDataInicio(dataInicio);
        turma.setDataTermino(dataTermino);
        turma.setQtdeAvaliacao(qtdeAvaliacao);
        turma.setSala(sala);
        turma.setDisciplina(disciplina);
        turma.setProfessor(professor);
        return turma;
    }

    public TurmaBuilder more(String sigla, String dataInicio, String dataTermino, int qtdeAvaliacao, Sala sala, Disciplina disciplina, Professor professor) {
        try {
            Calendar calendarDataInicio = Calendar.getInstance();
            calendarDataInicio.setTime(strToDate.parse(dataInicio));
            Calendar calendarDataTermino = Calendar.getInstance();
            calendarDataTermino.setTime(strToDate.parse(dataTermino));
            turmas.add(create(sigla, calendarDataInicio, calendarDataTermino, qtdeAvaliacao, sala, disciplina, professor));
            return this;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Turma buildOne() { return turmas.get(0);}

    public List<Turma> buildAll() {
        return turmas;
    }


}
