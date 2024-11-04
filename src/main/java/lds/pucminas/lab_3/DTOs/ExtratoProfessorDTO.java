package lds.pucminas.lab_3.DTOs;

import java.util.List;

import lds.pucminas.lab_3.models.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor     
public class ExtratoProfessorDTO {
    private int saldoMoedas;
    private List<Transacao> transacoes;
}
