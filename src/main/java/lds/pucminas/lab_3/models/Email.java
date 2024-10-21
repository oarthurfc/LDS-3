package lds.pucminas.lab_3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinatario;
    private String assunto;
    private String corpo;
    private String codigoCupom;

    @ManyToOne
    @JoinColumn(name = "vantagem_id")
    private Vantagem vantagem;

    public void enviarEmail() {
        // Código para enviar email pode ser implementado aqui
    }
}
