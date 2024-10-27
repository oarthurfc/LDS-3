package lds.pucminas.lab_3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public void enviarNotificacao() {
        // Código para enviar notificação pode ser implementado aqui
    }
}