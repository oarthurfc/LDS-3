package lds.pucminas.lab_3.DTOs;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransacaoProfessorDTO {
    private Long id;
    private String tipo;
    private int montante;
    private Date data;
    private AlunoResumoDTO aluno;  
}
