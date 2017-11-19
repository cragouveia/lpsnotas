package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.consultaaluno;

import br.ufms.facom.des.g2.lpsnotas.persistencia.dao.AlunoDAO;
import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Aluno;

import java.util.List;

public class ConsultaAlunoGraduacao implements ConsultaAluno {

    private AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    public List<Aluno> listarAlunos() throws Exception{
        return alunoDAO.listarAlunosGraduacao();
    }
}
