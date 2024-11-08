package lds.pucminas.lab_3.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor     
public class ExtratoAlunoDTO {
    private int saldoMoedas;
    private List<TransacaoAlunoDTO> transacoes;
}
