package br.com.gvendas.controller;

import br.com.gvendas.dto.CategoriaDTO;
import br.com.gvendas.dto.converter.CategoriaDTOConverter;
import br.com.gvendas.model.Categoria;
import br.com.gvendas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaDTOConverter categoriaDTOConverter;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<Categoria> categorias = categoriaService.listar();
        return ResponseEntity.ok().body(categoriaDTOConverter.toList(categorias));
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long idCategoria) {
        var categoria = categoriaService.buscarPorId(idCategoria);
        return ResponseEntity.ok().body(categoriaDTOConverter.to(categoria.get()));
    }

}
