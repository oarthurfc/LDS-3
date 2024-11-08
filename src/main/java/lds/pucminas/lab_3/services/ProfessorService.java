package lds.pucminas.lab_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lds.pucminas.lab_3.DTOs.AlunoResumoDTO;
import lds.pucminas.lab_3.DTOs.ExtratoProfessorDTO;
import lds.pucminas.lab_3.DTOs.ProfessorResumoDTO;
import lds.pucminas.lab_3.DTOs.TransacaoProfessorDTO;
import lds.pucminas.lab_3.models.Aluno;
import lds.pucminas.lab_3.models.Professor;
import lds.pucminas.lab_3.models.Transacao;
import lds.pucminas.lab_3.repositories.ProfessorRepository;
import lds.pucminas.lab_3.repositories.TransacaoRepository;
import lds.pucminas.lab_3.repositories.AlunoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    } 

    public Professor atualizar(Long id, Professor professorAtualizado) {
        return professorRepository.findById(id)
            .map(professor -> {
                professor.setNome(professorAtualizado.getNome());
                professor.setEmail(professorAtualizado.getEmail());
                professor.setSenha(professorAtualizado.getSenha());
                professor.setCpf(professorAtualizado.getCpf());
                professor.setDepartamento(professorAtualizado.getDepartamento());
                professor.setSaldoMoedas(professorAtualizado.getSaldoMoedas());
                return professorRepository.save(professor);
            }).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }

    public void enviarMoedas(Long professorId, Long alunoId, int montante, String motivo){
        Professor professor = professorRepository.findById(professorId)
        .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Aluno aluno = alunoRepository.findById(alunoId)
        .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (professor.getSaldoMoedas() < montante) {
            throw new RuntimeException("Saldo de moedas insuficiente");
        }

        professor.setSaldoMoedas(professor.getSaldoMoedas() - montante);
        professorRepository.save(professor);

        aluno.setSaldoMoedas(aluno.getSaldoMoedas() + montante);
        alunoRepository.save(aluno);

        Transacao transacao = new Transacao();
        transacao.setTipo("Envio de moedas");
        transacao.setMontante(montante);
        transacao.setAluno(aluno);
        transacao.setProfessor(professor);
        transacao.setData(new Date());

        transacaoRepository.save(transacao);
    }

    public ExtratoProfessorDTO consultarExtrato(Long professorId) {
        Professor professor = professorRepository.findById(professorId)
        .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        List<Transacao> transacoes = transacaoRepository.findByProfessorId(professorId);
        int saldo = professor.getSaldoMoedas();

        List<TransacaoProfessorDTO> trasacoesDTO = transacoes.stream().map(transacao ->{
                AlunoResumoDTO alunoResumo = new AlunoResumoDTO(
                    transacao.getAluno().getId(),
                    transacao.getAluno().getNome()
                );

                ProfessorResumoDTO professorResumo = new ProfessorResumoDTO(
                    transacao.getProfessor().getId(),
                    transacao.getProfessor().getNome()
                );

                return new TransacaoProfessorDTO(
                    transacao.getId(),
                    transacao.getTipo(),
                    transacao.getMontante(),
                    transacao.getData(),
                    professorResumo,
                    alunoResumo
                );

        }).collect(Collectors.toList());
        
        return new ExtratoProfessorDTO(saldo, trasacoesDTO);
    }

}
