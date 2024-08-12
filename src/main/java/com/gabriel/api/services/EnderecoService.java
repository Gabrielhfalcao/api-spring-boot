package com.gabriel.api.services;

import com.gabriel.api.endereco.EnderecoModel;
import com.gabriel.api.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoModel> findAll() {
        return enderecoRepository.findAll();
    }

    public String create(EnderecoModel endereco) {
        enderecoRepository.save(endereco);
        return "Created";
    }

}
