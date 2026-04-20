package com.juans.prueba_tecnica.shared.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

// Maneja errores de forma centralizada para respuestas HTTP consistentes.
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleWebExchangeBindException(WebExchangeBindException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Solicitud inválida");
        problemDetail.setDetail(ex.getReason() != null ? ex.getReason() : "Los datos enviados no son válidos");
        return problemDetail;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Error de validación");
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handleResponseStatusException(ResponseStatusException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getStatusCode());
        problemDetail.setTitle("Error de solicitud");
        problemDetail.setDetail(ex.getReason() != null ? ex.getReason() : "La solicitud no pudo procesarse");
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleGenericException(Exception ex) {
        log.error("Error no manejado", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Error interno");
        String message = ex.getMessage();
        problemDetail.setDetail(
                message != null && !message.isBlank()
                        ? message
                        : "Ocurrió un error inesperado");
        return problemDetail;
    }
}