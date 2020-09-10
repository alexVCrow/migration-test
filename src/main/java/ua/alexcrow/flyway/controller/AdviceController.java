package ua.alexcrow.flyway.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ua.alexcrow.flyway.dto.ErrorInfo;
import ua.alexcrow.flyway.exception.TransferNotFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class AdviceController {

    @ExceptionHandler(value = TransferNotFoundException.class)
    protected ResponseEntity<Object> handleTransferNotFoundException(TransferNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInfo(ex.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorInfo("Incorrect fields: " + ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(";"))));
    }

    @ExceptionHandler(value = DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest webRequest) {
        log.error("Error DataBase {}", webRequest.getDescription(true), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorInfo("DataBase error!"));
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest) {
        log.error("Error unknown {}", webRequest.getDescription(true), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorInfo("Unknown error!"));
    }

}
