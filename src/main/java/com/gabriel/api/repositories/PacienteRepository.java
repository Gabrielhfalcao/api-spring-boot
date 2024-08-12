package com.gabriel.api.repositories;

import com.gabriel.api.pacientes.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
}
