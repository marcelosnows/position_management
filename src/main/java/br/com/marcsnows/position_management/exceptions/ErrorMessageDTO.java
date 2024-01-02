package br.com.marcsnows.position_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // cria um construtor com argumentos

public class ErrorMessageDTO {

  private String message; // pega somente a msg
  private String field; // o campo onde ocorreu o erro

};
