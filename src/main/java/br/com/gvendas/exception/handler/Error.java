package br.com.gvendas.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Error {

    private String mensagemUsuario;
    private String mensagemDesenvolvedor;

}
