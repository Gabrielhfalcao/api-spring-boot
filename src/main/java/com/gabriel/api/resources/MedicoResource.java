package com.gabriel.api.resources;

import com.gabriel.api.medico.*;
import com.gabriel.api.services.MedicoSevice;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoResource {

    @Autowired
    private MedicoSevice medicoService;
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhadosMedico> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(medicoService.findById(id));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> findAll(@PageableDefault(size=10, page=0, sort={"id"}) Pageable pageable) {
        return ResponseEntity.ok().body(medicoService.findAll(pageable));
    }
    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosDetalhadosMedico> cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        DadosDetalhadosMedico medico = medicoService.create(dados);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.id()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosCadastroMedico> updateMedico(@RequestBody @Valid DadosUpdateMedico dados){
        return ResponseEntity.ok().body(medicoService.update(dados));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
