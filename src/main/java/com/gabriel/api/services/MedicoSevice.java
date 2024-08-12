package com.gabriel.api.services;

import com.gabriel.api.endereco.Endereco;
import com.gabriel.api.endereco.EnderecoModel;
import com.gabriel.api.medico.*;
import com.gabriel.api.repositories.EnderecoRepository;
import com.gabriel.api.repositories.MedicoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoSevice {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Page<DadosListagemMedico> findAll(Pageable pageable) {
       return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    public DadosDetalhadosMedico create(DadosCadastroMedico medico) {
        MedicoModel medicoModel = new MedicoModel();
        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModel.setLogradouro(medico.endereco().logradouro());
        enderecoModel.setBairro(medico.endereco().bairro());
        enderecoModel.setCep(medico.endereco().cep());
        enderecoModel.setCidade(medico.endereco().cidade());
        enderecoModel.setUf(medico.endereco().uf());
        enderecoModel.setComplemento(medico.endereco().complemento());
        enderecoModel.setNumero(medico.endereco().numero());

        medicoModel.setAtivo(true);
        medicoModel.setNome(medico.nome());
        medicoModel.setEmail(medico.email());
        medicoModel.setCrm(medico.crm());
        medicoModel.setEspecialidade(medico.especialidade());
        medicoModel.setEndereco(enderecoModel);
        medicoRepository.save(medicoModel);

        return new DadosDetalhadosMedico(
                medicoModel.getId(),
                medico.nome(),
                medico.email(),
                medico.crm(),
                medico.especialidade(),
                medico.endereco());
    }

    public DadosCadastroMedico update(DadosUpdateMedico dados) {
        MedicoModel medico =  medicoRepository.getReferenceById(dados.id());
        EnderecoModel endereco = enderecoRepository.getReferenceById(medico.getEndereco().getId());
        if(dados.nome() != null){
            medico.setNome(dados.nome());
        }
        if(dados.endereco() != null){
            endereco.setNumero(dados.endereco().getNumero());
            endereco.setCep(dados.endereco().getCep());
            endereco.setBairro(dados.endereco().getBairro());
            endereco.setUf(dados.endereco().getUf());
            endereco.setLogradouro(dados.endereco().getLogradouro());
            endereco.setComplemento(dados.endereco().getComplemento());
            endereco.setCidade(dados.endereco().getCidade());
        }
        medicoRepository.save(medico);

        return new DadosCadastroMedico(
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade(),
                new Endereco(
                        medico.getEndereco().getLogradouro(),
                        medico.getEndereco().getBairro(),
                        medico.getEndereco().getCep(),
                        medico.getEndereco().getCidade(),
                        medico.getEndereco().getUf(),
                        medico.getEndereco().getNumero(),
                        medico.getEndereco().getComplemento()
                ));
    }

    public void delete(Long id) {
        MedicoModel medicoModel = medicoRepository.getReferenceById(id);
        medicoModel.setAtivo(false);
    }

    public DadosDetalhadosMedico findById(Long id) {
        MedicoModel medicoModel = medicoRepository.getReferenceById(id);
        return new DadosDetalhadosMedico(medicoModel);
    }
}
