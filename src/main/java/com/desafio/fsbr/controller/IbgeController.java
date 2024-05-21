package com.desafio.fsbr.controller;

import com.desafio.fsbr.model.EstadoModel;
import com.desafio.fsbr.model.MunicipioModel;
import com.desafio.fsbr.service.IbgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ibge")
public class IbgeController {

    @Autowired
    private IbgeService ibgeService;

    @GetMapping("/estados")
    public List<EstadoModel> getEstados() {
        return ibgeService.getEstados();
    }

    @GetMapping("/estados/{uf}/municipios")
    public List<MunicipioModel> getMunicipios(@PathVariable String uf) {
        return ibgeService.getMunicipiosPorEstado(uf);
    }
}
