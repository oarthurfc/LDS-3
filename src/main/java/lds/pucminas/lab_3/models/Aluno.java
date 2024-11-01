package lds.pucminas.lab_3.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    private String cpf;
    private String rg;
    private String endereco;
    private String instituicao;
    private String curso;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno")
    private List<Transacao> transacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno")
    private List<Notificacao> notificacoes;

}
