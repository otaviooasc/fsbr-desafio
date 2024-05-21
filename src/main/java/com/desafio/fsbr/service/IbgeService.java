package com.desafio.fsbr.service;

import com.desafio.fsbr.model.Estado;
import com.desafio.fsbr.model.Municipio;
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

    public List<Estado> getEstados() {
        Estado[] estados = restTemplate.getForObject(ibgeEstadosUrl, Estado[].class);
        return Arrays.asList(estados);
    }

    public List<Municipio> getMunicipiosPorEstado(String uf) {
        Municipio[] municipios = restTemplate.getForObject(ibgeMunicipioUrl, Municipio[].class, uf);
        return Arrays.asList(municipios);
    }
}
