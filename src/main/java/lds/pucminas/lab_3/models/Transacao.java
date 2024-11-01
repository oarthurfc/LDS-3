package lds.pucminas.lab_3.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private int montante;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)

    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = true)
    private Aluno aluno;
}
