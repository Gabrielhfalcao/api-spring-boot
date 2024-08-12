package com.gabriel.api.pacientes;

import com.gabriel.api.endereco.EnderecoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotNull
        @Valid
        EnderecoModel endereco
) {
}
