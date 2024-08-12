package com.gabriel.api.pacientes;

import com.gabriel.api.endereco.EnderecoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhadosPaciente(
        Long id,
        String nome,
        String email,
        EnderecoModel endereco
) {
    public DadosDetalhadosPaciente(PacienteModel paciente){
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getEndereco()
        );
    }
}
