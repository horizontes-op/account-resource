package insper.store.account.exceptions;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(NullPointerException.class)
        private ResponseEntity<RestErrorMessage> handleNullPointerException(NullPointerException ex) {
            RestErrorMessage error = new RestErrorMessage(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor: null pointer exception.");
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(error);
        };

        @ExceptionHandler(IndexOutOfBoundsException.class)
        private ResponseEntity<RestErrorMessage> handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
            RestErrorMessage error = new RestErrorMessage(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor: Índice fora do intervalo.");
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(error);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        private ResponseEntity<RestErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
            RestErrorMessage error = new RestErrorMessage(HttpStatus.SC_BAD_REQUEST, "Requisição inválida: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(error);
        }
        @ExceptionHandler(UnsupportedOperationException.class)
        private ResponseEntity<RestErrorMessage> handleUnsupportedOperationException(UnsupportedOperationException ex) {
            RestErrorMessage error = new RestErrorMessage(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Operação não suportada: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(error);
        }
        @ExceptionHandler(DataIntegrityViolationException.class)
        private ResponseEntity<RestErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
            RestErrorMessage error = new RestErrorMessage(HttpStatus.SC_BAD_REQUEST, "Violação de integridade de dados: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(error);
        }
}
