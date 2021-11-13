package br.com.gvendas.service;

import br.com.gvendas.model.Categoria;
import br.com.gvendas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }


}
