package com.gabriel.api.medico;

import com.gabriel.api.endereco.EnderecoModel;
import jakarta.validation.constraints.NotNull;

public record DadosUpdateMedico(
        @NotNull
        Long id,
        String nome,
        EnderecoModel endereco
) {
}
