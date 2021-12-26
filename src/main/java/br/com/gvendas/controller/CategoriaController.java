package br.com.gvendas.controller;

import br.com.gvendas.dto.CategoriaDTO;
import br.com.gvendas.dto.converter.CategoriaDTOConverter;
import br.com.gvendas.model.Categoria;
import br.com.gvendas.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaDTOConverter categoriaDTOConverter;

    @ApiOperation(value = "Listar")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<Categoria> categorias = categoriaService.listar();
        return ResponseEntity.ok().body(categoriaDTOConverter.toList(categorias));
    }

    @ApiOperation(value = "Buscar por ID")
    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long idCategoria) {
        var categoria = categoriaService.buscarPorId(idCategoria);
        return ResponseEntity.ok().body(categoriaDTOConverter.to(categoria));
    }

    @ApiOperation(value = "Criar nova categoria")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoriaDTO> salvar(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaDTOConverter.to(categoriaDTO);
        categoria = categoriaService.salvar(categoria);
        return ResponseEntity.ok().body(categoriaDTOConverter.to(categoria));
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long idCategoria, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoriaAtual = categoriaService.buscarPorId(idCategoria);
        categoriaAtual = categoriaDTOConverter.to(categoriaDTO);
        categoriaAtual = categoriaService.salvar(categoriaAtual);
        return ResponseEntity.ok().body(categoriaDTOConverter.to(categoriaAtual));
    }

}
