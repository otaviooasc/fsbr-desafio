package com.desafio.fsbr.controller;

import com.desafio.fsbr.dto.ProcessoDTO;
import com.desafio.fsbr.model.ProcessoModel;
import com.desafio.fsbr.service.ProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/processo")
public class ProcessosController {
    @Autowired
    private ProcessoService service;

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarProcesso(@RequestBody @Valid ProcessoDTO dto) {
        try {
            var processoModel = new ProcessoModel();
            BeanUtils.copyProperties(dto, processoModel);
            processoModel.setDataVisualizacao(LocalDate.now());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarProcesso(processoModel));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar: " + ex.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listarProcessos(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(service.listarProcessos(page, size));
    }
}
