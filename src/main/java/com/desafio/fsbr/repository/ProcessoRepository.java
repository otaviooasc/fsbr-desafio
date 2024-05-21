package com.desafio.fsbr.repository;

import com.desafio.fsbr.model.ProcessoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository  extends JpaRepository<ProcessoModel, String> {
}
