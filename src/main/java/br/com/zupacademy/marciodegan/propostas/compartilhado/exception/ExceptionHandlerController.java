package br.com.zupacademy.marciodegan.propostas.compartilhado.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)  // trata todas excecoes com @Valid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<?> gerarRespostaDeErro(MethodArgumentNotValidException exception) {
        List<Map<String, String>> respostas = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(FieldError -> {
            Map<String, String> erroDto = new HashMap<>();
            erroDto.put("campo", FieldError.getField());
            erroDto.put("mensagem", FieldError.getDefaultMessage());
            erroDto.put("toString", FieldError.toString());
            erroDto.put("getCode", FieldError.getCode());
            erroDto.put("getObjectName", FieldError.getObjectName());
            respostas.add(erroDto);
        });
        return respostas;
    }

    @ExceptionHandler(FeignExceptionError.class)
    public ResponseEntity<ErroPadronizado> handleValidationError(FeignExceptionError exception){
        Collection<String> mensagens = new ArrayList<>();
        mensagens.add(exception.getReason());

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
        return ResponseEntity.status(exception.getHttpStatus()).body(erroPadronizado);
    }
}