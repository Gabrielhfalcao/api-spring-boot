package com.gabriel.api.services;

import com.gabriel.api.consulta.Consulta;
import com.gabriel.api.consulta.DadosAgendamentoConsulta;
import com.gabriel.api.consulta.DadosDetalhamentoConsulta;
import com.gabriel.api.consulta.validador.ValidadorRegrasConsulta;
import com.gabriel.api.medico.DadosDetalhadosMedico;
import com.gabriel.api.medico.MedicoModel;
import com.gabriel.api.pacientes.DadosDetalhadosPaciente;
import com.gabriel.api.pacientes.PacienteModel;
import com.gabriel.api.repositories.ConsultaRepository;
import com.gabriel.api.repositories.MedicoRepository;
import com.gabriel.api.repositories.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorRegrasConsulta> validacoes;

    public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dados) {
        if(dados != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidationException("Id do médico informado não existe");
        }
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidationException("Id do paciente informado não existe");
        }

        for(ValidadorRegrasConsulta validador : validacoes){
            validador.validar(dados);
        }

        MedicoModel medico = escolherMedico(dados);
        DadosDetalhadosMedico dadosMedico = new DadosDetalhadosMedico(medico);
        PacienteModel paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        DadosDetalhadosPaciente dadospaciente = new DadosDetalhadosPaciente(paciente);
        Consulta consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta, dadospaciente, dadosMedico);
    }
    private MedicoModel escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidationException("A especialidade é obrigatória quando o médico não for informado");
        }
        return medicoRepository.escolherMedicoAleatorio(dados.especialidade(), dados.data());
    }
}
