package br.com.gvendas.exception.handler;

import br.com.gvendas.exception.CategoriaNaoEncontradaException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> errors = obterErros(ex.getBindingResult());

        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String msgUsuario =  "Recurso não encontrado";
        String msgDesenvolvedor =  ex.toString();
        List<Error> erros = Arrays.asList(new Error(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    private List<Error> obterErros(BindingResult bindingResult) {
        List<Error> erros = new ArrayList<>();

        bindingResult.getFieldErrors().stream().forEach(fieldError -> {
            String msgUsuario = tratarMensagemError(fieldError);
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Error(msgUsuario, msgDesenvolvedor));
        });

        return erros;

    }

    private String tratarMensagemError(FieldError fieldError) {
        if (fieldError.getCode().equals("NotBlank")) {
            return fieldError.getDefaultMessage().concat(" É obrigatório");
        }

        if (fieldError.getCode().equals("Length")) {
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre 3 e 50 caracteres"));
        }
        return fieldError.toString();
    }
}
