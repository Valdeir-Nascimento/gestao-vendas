package br.com.gvendas.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {
    public CategoriaNaoEncontradaException(String message) {
        super(message);
    }

    public CategoriaNaoEncontradaException(Long idCategoria) {
        this(String.format("Não existe categoria com Id: ", idCategoria));
    }
}
