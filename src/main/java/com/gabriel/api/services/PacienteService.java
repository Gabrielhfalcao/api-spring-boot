package com.gabriel.api.services;

import com.gabriel.api.endereco.EnderecoModel;
import com.gabriel.api.medico.DadosDetalhadosMedico;
import com.gabriel.api.pacientes.*;
import com.gabriel.api.repositories.EnderecoRepository;
import com.gabriel.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Page<DadosListagemPaciente> findAll(Pageable pageable){
        return pacienteRepository.findAll(pageable).map(DadosListagemPaciente::new);
    }

    public DadosDetalhadosPaciente create(DadosCadastroPaciente paciente) {
        PacienteModel pacienteModel = new PacienteModel();
        pacienteModel.setAtivo(true);
        pacienteModel.setNome(paciente.nome());
        pacienteModel.setEmail(paciente.email());
        pacienteModel.setEndereco(paciente.endereco());

        pacienteRepository.save(pacienteModel);
        return new DadosDetalhadosPaciente(pacienteModel);
    }

    public DadosDetalhadosPaciente findById(Long id) {
        PacienteModel paciente = pacienteRepository.getReferenceById(id);
        return new DadosDetalhadosPaciente(paciente);
    }

    public DadosDetalhadosPaciente update(DadosUpdatePaciente dados) {
        PacienteModel paciente = pacienteRepository.getReferenceById(dados.id());
        EnderecoModel endereco = enderecoRepository.getReferenceById(paciente.getEndereco().getId());

        if(dados.nome() != null){
            paciente.setNome(dados.nome());
        }
        if(dados.endereco() != null){
            endereco.setNumero(dados.endereco().numero());
            endereco.setCep(dados.endereco().cep());
            endereco.setBairro(dados.endereco().bairro());
            endereco.setUf(dados.endereco().uf());
            endereco.setLogradouro(dados.endereco().logradouro());
            endereco.setComplemento(dados.endereco().complemento());
            endereco.setCidade(dados.endereco().cidade());
        }
        pacienteRepository.save(paciente);
        return new DadosDetalhadosPaciente(paciente);
    }

    public void delete(Long id) {
        PacienteModel paciente = pacienteRepository.getReferenceById(id);
        paciente.setAtivo(false);
    }
}
