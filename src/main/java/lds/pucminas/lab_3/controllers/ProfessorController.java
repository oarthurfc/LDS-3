package lds.pucminas.lab_3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lds.pucminas.lab_3.models.Professor;
import lds.pucminas.lab_3.services.ProfessorService;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> listarTodos() {
        return professorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return professorService.buscarPorId(id)
            .map(professor -> ResponseEntity.ok().body(professor))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody Professor professor) {
        Professor novoProfessor = professorService.salvar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody Professor professorAtualizado) {
        try {
            Professor professorAtualizadoObj = professorService.atualizar(id, professorAtualizado);
            return ResponseEntity.ok().body(professorAtualizadoObj);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{professorId}/enviar-moedas/{alunoId}")
    public ResponseEntity<String> enviarMoedas(@PathVariable Long professorId, @PathVariable Long alunoId, @RequestParam int montante, @RequestParam String motivo) {
        try {
            professorService.enviarMoedas(professorId, alunoId, montante, motivo);
            return ResponseEntity.ok("Moedas enviadas com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }  
    }    
}
