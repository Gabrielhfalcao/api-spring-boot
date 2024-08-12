package com.gabriel.api.medico;

import com.gabriel.api.endereco.EnderecoModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @OneToOne(cascade = CascadeType.PERSIST)
    private EnderecoModel endereco;
    private Boolean ativo;

    public MedicoModel(DadosCadastroMedico cadastroMedico){
        this.nome = cadastroMedico.nome();
        this.email = cadastroMedico.email();
        this.crm = cadastroMedico.crm();
        this.especialidade = cadastroMedico.especialidade();
    }
}