package com.desafio.fsbr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record ProcessoDTO(@NotBlank String npu,
                          @NotBlank String municipio,
                          @NotBlank String uf,
                          MultipartFile docPDF
) {}
