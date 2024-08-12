package com.gabriel.api.pacientes;

public record DadosListagemPaciente(
        String nome,
        String email
) {
    public DadosListagemPaciente(PacienteModel pacienteModel){
        this(pacienteModel.getNome(), pacienteModel.getEmail());
    }
}
