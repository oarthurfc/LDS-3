package lds.pucminas.lab_3.models;

import jakarta.persistence.*;
import lds.pucminas.lab_3.enums.TipoAcesso;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoAcesso tipoAcesso;

}
