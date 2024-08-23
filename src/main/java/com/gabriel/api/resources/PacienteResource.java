package com.gabriel.api.resources;

import com.gabriel.api.pacientes.*;
import com.gabriel.api.services.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteResource {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhadosPaciente> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(pacienteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> findAll(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable){
        return ResponseEntity.ok().body(pacienteService.findAll(pageable));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<DadosDetalhadosPaciente> create(@RequestBody @Valid DadosCadastroPaciente paciente, UriComponentsBuilder uriBuilder){
        DadosDetalhadosPaciente pacienteDetalhado = pacienteService.create(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteDetalhado.id()).toUri();

        return ResponseEntity.created(uri).body(pacienteDetalhado);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhadosPaciente> update(@RequestBody @Valid DadosUpdatePaciente dados){
        return ResponseEntity.ok().body(pacienteService.update(dados));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhadosPaciente> delete(@PathVariable Long id){
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
