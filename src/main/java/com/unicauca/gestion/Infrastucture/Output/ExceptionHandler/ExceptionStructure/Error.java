package com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Error {

  /**
   * Código de error de la aplicación
   */
  private String errorCode;
  /**
   * Mensaje de error generado
   */
  private String message;
  /**
   * Código de estatus http
   */
  private Integer httpCode;
  /**
   * Url de la petición que generó el error
   */
  @Accessors(chain = true)
  private String url;
  /**
   * Método de la petición que generó el error
   */
  @Accessors(chain = true)
  private String method;
}

