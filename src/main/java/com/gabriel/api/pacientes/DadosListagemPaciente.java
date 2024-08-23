package com.gabriel.api.pacientes;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email
) {
    public DadosListagemPaciente(PacienteModel pacienteModel){
        this(   pacienteModel.getId(),
                pacienteModel.getNome(),
                pacienteModel.getEmail());
    }
}
