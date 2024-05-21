package com.desafio.fsbr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String npu;

    private LocalDate dataVisualizacao;

    private String municipio;

    private String uf;

    private String docPDF;
}
