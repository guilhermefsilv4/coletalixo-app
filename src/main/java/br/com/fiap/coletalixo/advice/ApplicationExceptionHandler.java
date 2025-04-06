package br.com.fiap.coletalixo.advice;

import br.com.fiap.coletalixo.exception.AgendamentoConcluidoException;
import br.com.fiap.coletalixo.exception.AgendamentoNotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fields = error.getBindingResult().getFieldErrors();

        for (FieldError field : fields) {
            errorMap.put(field.getField(), field.getDefaultMessage());
        }

        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegrityViolation() {
        return Collections.singletonMap("erro", "Usuário já cadastrado!");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AgendamentoConcluidoException.class)
    public Map<String, String> handleAgendamentoConcluidoException() {
        return Collections.singletonMap("erro", "Não é possível alterar um agendamento já concluido!");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AgendamentoNotFound.class)
    public Map<String, String> handleNotFound(AgendamentoNotFound ex) {
        return Collections.singletonMap("erro", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleAllExceptions(Exception ex) {
        return Collections.singletonMap("erro", ex.getMessage());
    }
}