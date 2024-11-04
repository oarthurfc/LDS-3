package lds.pucminas.lab_3.controllers;

import lds.pucminas.lab_3.DTOs.ExtratoAlunoDTO;
import lds.pucminas.lab_3.models.Aluno;
import lds.pucminas.lab_3.services.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(description="Cria um aluno")
    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.createAluno(aluno);
    }

    @Operation(description="Retorna um aluno por id")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoService.getAlunoById(id);
        if (alunoOptional.isPresent()) {
            return ResponseEntity.ok(alunoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Retorna todos os alunos")
    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @Operation(description = "Altera um aluno")
    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
        return alunoService.updateAluno(id, alunoDetails);
    }

    @Operation(description="Deleta um aluno")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<ExtratoAlunoDTO> consultarExtrato(@PathVariable Long id) {
        ExtratoAlunoDTO extrato = alunoService.consultarExtrato(id);
        return ResponseEntity.ok(extrato);
    }
}

