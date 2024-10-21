package lds.pucminas.lab_3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String foto;
    private int custo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public String gerarCodigo() {
        // Geração de código (podemos usar UUID, por exemplo)
        return java.util.UUID.randomUUID().toString();
    }
}
