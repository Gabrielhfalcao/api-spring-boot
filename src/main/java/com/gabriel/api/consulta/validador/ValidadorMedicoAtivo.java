package com.gabriel.api.consulta.validador;

import com.gabriel.api.consulta.DadosAgendamentoConsulta;
import com.gabriel.api.medico.MedicoModel;
import com.gabriel.api.repositories.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorRegrasConsulta{
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        if(dadosAgendamentoConsulta.idMedico() == null){
            return;
        }
        Boolean medicoEstaAtivo = medicoRepository.findAtivoById(dadosAgendamentoConsulta.idMedico());
        if(medicoEstaAtivo == false){
            throw new ValidationException("Não é possível agendar consulta com médico inativo");
        }
    }
}
