package com.desafio.fsbr.service;

import com.desafio.fsbr.model.EstadoModel;
import com.desafio.fsbr.model.MunicipioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class IbgeService {
    @Value("${api.url.ibge.estados}")
    private String ibgeEstadosUrl;

    @Value("${api.url.ibge.municipio}")
    private String ibgeMunicipioUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<EstadoModel> getEstados() {
        EstadoModel[] estados = restTemplate.getForObject(ibgeEstadosUrl, EstadoModel[].class);
        return Arrays.asList(estados);
    }

    public List<MunicipioModel> getMunicipiosPorEstado(String uf) {
        MunicipioModel[] municipios = restTemplate.getForObject(ibgeMunicipioUrl, MunicipioModel[].class, uf);
        return Arrays.asList(municipios);
    }
}
