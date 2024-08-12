package com.gabriel.api.pacientes;

import com.gabriel.api.endereco.EnderecoModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    private EnderecoModel endereco;

    private Boolean ativo;
}
