package lds.pucminas.lab_3.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lds.pucminas.lab_3.models.Aluno;
import lds.pucminas.lab_3.repositories.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno createAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }

    public List<Aluno> getAllAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno updateAluno(Long id, Aluno alunoDetails) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        aluno.setCpf(alunoDetails.getCpf());
        aluno.setCurso(alunoDetails.getCurso());
        aluno.setEmail(alunoDetails.getEmail());
        aluno.setEndereco(alunoDetails.getEndereco());
        aluno.setInstituicao(alunoDetails.getInstituicao());
        aluno.setNotificacoes(alunoDetails.getNotificacoes());
        aluno.setNome(alunoDetails.getNome());
        aluno.setSaldoMoedas(alunoDetails.getSaldoMoedas());
        return alunoRepository.save(aluno);
    }

    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }

}
