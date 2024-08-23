package com.gabriel.api.consulta;

import com.gabriel.api.medico.DadosDetalhadosMedico;
import com.gabriel.api.medico.Especialidade;
import com.gabriel.api.pacientes.DadosDetalhadosPaciente;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        DadosDetalhadosMedico medico,
        DadosDetalhadosPaciente paciente,
        LocalDateTime data,
        Especialidade especialidade
) {
    public DadosDetalhamentoConsulta (Consulta consulta, DadosDetalhadosPaciente paciente, DadosDetalhadosMedico medico) {
        this(
                consulta.getId(),
                medico,
                paciente,
                consulta.getData(),
                consulta.getMedico().getEspecialidade()
        );
    }
}
