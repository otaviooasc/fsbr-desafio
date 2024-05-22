package com.desafio.fsbr.controller;

import com.desafio.fsbr.exception.ResourceNotFoundException;
import com.desafio.fsbr.model.ProcessoModel;
import com.desafio.fsbr.service.ProcessoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/processo")
public class ProcessosController {
    @Autowired
    private ProcessoService service;

    @Value("${api.pasta.upload}")
    private String uploadDir;

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarProcesso(
            @RequestParam @NotBlank String npu,
            @RequestParam @NotBlank String municipio,
            @RequestParam @NotBlank String uf,
            @RequestParam("documentoPdf") @NotNull MultipartFile documentoPdf) throws IOException {
        try {
            String fileName = salvarUploadPDF(documentoPdf);

            ProcessoModel processo = new ProcessoModel();
            processo.setNpu(npu);
            processo.setMunicipio(municipio);
            processo.setUf(uf);
            processo.setDocPDF(fileName);

            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarProcesso(processo));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar: " + ex.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listarProcessos(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(service.listarProcessos(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterProcesso(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(service.obterProcesso(id));
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable String id) {
        service.deletarProcesso(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Object> alterarProcessoVisualizado(@PathVariable String id) {
        try {
            var processoById = (ProcessoModel) service.obterProcesso(id);
            processoById.setDataVisualizacao(LocalDate.now());
            return ResponseEntity.ok().body(service.atualizarProcesso(processoById));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Processo não encontrado: " + ex.getMessage());
        }
    }

    private String salvarUploadPDF(MultipartFile documentoPdf) throws IOException {
        if (!documentoPdf.isEmpty()) {
            byte[] bytes = documentoPdf.getBytes();
            Path path = Paths.get(uploadDir + documentoPdf.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        }
        return null;
    }
}
