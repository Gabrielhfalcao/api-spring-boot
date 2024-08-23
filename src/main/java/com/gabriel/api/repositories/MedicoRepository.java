package com.gabriel.api.repositories;

import com.gabriel.api.medico.Especialidade;
import com.gabriel.api.medico.MedicoModel;
import io.micrometer.observation.ObservationFilter;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    Page<MedicoModel> findAllByAtivoTrue(Pageable pageable);

    @Query("""
                select m from MedicoModel m
                where
                m.ativo = true
                and
                m.especialidade = :especialidade
                and
                m.id not in(
                        select c.medico.id from Consulta c
                        where
                        c.data = :data
                )
                order by rand()
                limit 1
                """)
    MedicoModel escolherMedicoAleatorio(Especialidade especialidade, LocalDateTime data);

    @Query("""
        select m.ativo
        from MedicoModel m
        where   
        m.id = :idMedico
""")
    Boolean findAtivoById(Long idMedico);
}
