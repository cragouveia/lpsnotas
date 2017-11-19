package br.ufms.facom.des.g2.lpsnotas.negocio.strategy.consultaaluno;

import br.ufms.facom.des.g2.lpsnotas.persistencia.domain.Aluno;

import java.util.List;

public interface ConsultaAluno {

    List<Aluno> listarAlunos() throws Exception;
}
