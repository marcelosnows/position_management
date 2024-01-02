package br.com.marcsnows.position_management.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

   // mapeamento da msg
   
   private MessageSource messageSource;

   /* necessário que ele inicialize já atribuindo o messageSource, qdo o Spring iniciar esse Controller, 
    não coloque o messageSource como (Null)*/

   public ExceptionHandlerController(MessageSource message) {
      this.messageSource = message;
   };
   
   // dessa forma, o Spring saberá exatamente que deve cair nesse método qdo apresentar essa Exception.
   @ExceptionHandler(MethodArgumentNotValidException.class) 
   public ResponseEntity<List<ErrorMessageDTO>> handleMethodNotValidArgument(MethodArgumentNotValidException e) {
      List<ErrorMessageDTO> dto = new ArrayList<>();
         //acesso a todos os erros.
         e.getBindingResult().getFieldErrors().forEach(err -> {

            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
               ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
               dto.add(error);
      });

      return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
   };   
};

