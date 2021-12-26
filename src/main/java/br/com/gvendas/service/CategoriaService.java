package br.com.gvendas.service;

import br.com.gvendas.exception.CategoriaNaoEncontradaException;
import br.com.gvendas.model.Categoria;
import br.com.gvendas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria).orElseThrow(() -> new CategoriaNaoEncontradaException(idCategoria));
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void excluir(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }


}
