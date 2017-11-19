package br.ufms.facom.des.g2.lpsnotas.negocio;

import br.ufms.facom.des.g2.lpsnotas.negocio.strategy.consultaaluno.ConsultaAluno;
import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.AlunoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Aluno;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Disciplina;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Turma;

import java.util.List;

public class GerenciarAluno extends Gerenciar<Aluno> {

    private ConsultaAluno consultaAluno;

    public GerenciarAluno(ConsultaAluno consultaAluno) {
        super(new AlunoDAO());
        this.consultaAluno = consultaAluno;
    }

    public List<Aluno> consultaAlunosPorTurma(Turma turma) throws Exception{
        try {
            return ((AlunoDAO) dao).consultarAlunosPorTurma(turma);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public List<Aluno> consultaAlunosPorDisciplina(Disciplina disciplina) throws Exception{
        try {
            return ((AlunoDAO) dao).consultarAlunosPorDisciplina(disciplina);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public List<Aluno> listarAlunos() throws Exception {
        try {
            return consultaAluno.listarAlunos();
        }
        catch (Exception e) {
            throw e;
        }
    }





}
