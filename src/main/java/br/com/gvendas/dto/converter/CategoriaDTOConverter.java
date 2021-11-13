package br.com.gvendas.dto.converter;

import br.com.gvendas.dto.CategoriaDTO;
import br.com.gvendas.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaDTOConverter {

    @Autowired
    private ModelMapper mapper;

    public CategoriaDTO to(Categoria categoria) {
        return mapper.map(categoria, CategoriaDTO.class);
    }

    public Categoria to(CategoriaDTO categoriaDTO) {
        return mapper.map(categoriaDTO, Categoria.class);
    }

    public List<CategoriaDTO> toList(List<Categoria> categorias) {
        return categorias.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }

}
