package com.gabriel.api.resources;

import com.gabriel.api.endereco.EnderecoModel;
import com.gabriel.api.services.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoModel> findAll() {
        return enderecoService.findAll();
    }

    @PostMapping("/create")
    @Transactional
    public String create(@RequestBody @Valid EnderecoModel endereco) {
        return enderecoService.create(endereco);
    }

}
