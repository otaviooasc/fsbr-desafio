package com.desafio.fsbr.service;

import com.desafio.fsbr.exception.ResourceNotFoundException;
import com.desafio.fsbr.model.ProcessoModel;
import com.desafio.fsbr.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository repository;

    public Object salvarProcesso(ProcessoModel processoModel) {
        return repository.save(processoModel);
    }

    public List<ProcessoModel> listarProcessos(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Object obterProcesso(String id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Processo não encontrado."));
    }

    public Object atualizarProcesso(ProcessoModel model) {
        return repository.save(model);
    }

    public void deletarProcesso(String id) {
        repository.deleteById(id);
    }
}
