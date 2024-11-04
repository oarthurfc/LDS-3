package lds.pucminas.lab_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lds.pucminas.lab_3.models.Professor;
import lds.pucminas.lab_3.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

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
}
