package lds.pucminas.lab_3.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    private String cpf;
    private String departamento;
    private int saldoMoedas = 1000;

    @OneToMany(mappedBy = "professor")
    private List<Transacao> transacoes;

}
