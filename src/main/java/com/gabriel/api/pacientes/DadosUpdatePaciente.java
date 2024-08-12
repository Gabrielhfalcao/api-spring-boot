package com.gabriel.api.pacientes;

import com.gabriel.api.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosUpdatePaciente(
        @NotNull
        Long id,
        String nome,
        Endereco endereco
) {
}
